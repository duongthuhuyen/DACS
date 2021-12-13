package com.news.orm.impl;


import com.mysql.cj.util.StringUtils;
import com.news.Utils.AnnotationUtil;

import com.news.orm.Annotation.Id;
import orm.JpaRepository;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.news.Utils.AnnotationUtil.*;
import static com.news.Utils.ReflectionUtil.get;
import static com.news.Utils.ReflectionUtil.mapToEntity;
import exception.OrmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import responsity.ConnectionManager;
//import static com.news.Utils.ReflectionUtil.mapToEntity;

public class BaseResponsity<T,ID extends Serializable> implements JpaRepository<T,ID> {
    private final String tableName;
    private final Class<T> tClass ;
    private  String insert;
    private  String update;
    private String select;
    private final String count;
    private String delete;

    public BaseResponsity() {
        this.tClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.tableName = getClassName(tClass);
        this.insert = "INSERT INTO "+tableName+"(%s) VALUES (%s)";
        this.update = "UPDATE "+tableName+ " set %s where %s";
        this.select = "SELECT * FROM "+tableName;
        this.count = "SELECT COUNT(1) as total FROM "+tableName;
        this.delete = "DELETE FROM "+tableName;
    }

    @Override
    public void save(final T t) {
        Field[] fields = tClass.getDeclaredFields();
        final StringBuilder columns = new StringBuilder();
        final StringBuilder values = new StringBuilder();
        Map<String,Integer> map = new HashMap<>();
        AtomicInteger index = new AtomicInteger(1);
        Arrays.stream(fields).forEach(field -> {
            if(field.isAnnotationPresent(Id.class)){
                boolean isAutoIncrement = AnnotationUtil.isAutoIncrement(tClass,field.getName());
                if(!isAutoIncrement){
                    String primaryName = primaryColumn(tClass,field.getName());
                    columns.append(primaryName).append(",");
                    values.append("?,");
                    map.put(field.getName(),index.getAndIncrement());
                }
            }else{
                String columnName = getColumnName(tClass,field.getName());
                columns.append(columnName).append(",");
                values.append("?,");
                map.put(field.getName(), index.getAndIncrement());
            }
        });
        String value = values.deleteCharAt(values.length()-1).toString();
        String column = columns.deleteCharAt(columns.length()-1).toString();
        insert = String.format(insert,column,value);
       
        ConnectionManager conn = new ConnectionManager();
        try{
            Connection connection = conn.getConnection();
            final PreparedStatement pr = connection.prepareStatement(insert);
            Arrays.stream(fields).forEach(field ->{
                if(field.isAnnotationPresent(Id.class)){
                    boolean isAutoCreatment = isAutoIncrement(tClass,field.getName());
                    if(!isAutoCreatment){
                        try {
                           // System.out.println(get(t,field));
                            pr.setObject(map.get(field.getName()),get(t,field));
                        } catch (SQLException e) {
                            throw new OrmException(e.getMessage());
                        }}
                    }else {
                        try {
                            //System.out.println(get(t,field));
                            pr.setObject(map.get(field.getName()), get(t, field));
                        } catch (SQLException e) {
                            throw new OrmException(e.getMessage());
                        }
                    }
                });
            
            pr.executeUpdate();
            //System.out.println(insert);
       
        } catch (SQLException ex) {
            Logger.getLogger(BaseResponsity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(ID id, T t) {
        StringBuilder set = new StringBuilder();
        StringBuilder condition = new StringBuilder();
        Field[] fields = tClass.getDeclaredFields();
        Map<String,Integer> map = new HashMap<>();
        AtomicInteger index = new AtomicInteger(1);
        AtomicReference<String> primary= new AtomicReference<>();
        Arrays.stream(fields).forEach(field -> {
            if(!field.isAnnotationPresent(Id.class)){
                String columnName = getColumnName(tClass,field.getName());
                if(StringUtils.isNullOrEmpty(columnName)){
                    throw new OrmException("column name is null");
                }
                set.append(columnName).append(" = ? ,");
                map.put(field.getName(),index.getAndIncrement());
            }else{
                String primaryName = primaryColumn(tClass,field.getName());
                if(StringUtils.isNullOrEmpty(primaryName)){
                    throw  new OrmException("PrimaryName is Null");
                }
                condition.append(primaryName).append(" =?");
                primary.set(field.getName());
            }
        });
        map.put(primary.get(),index.getAndIncrement());
        set.deleteCharAt(set.length()-1);
        this.update = String.format(this.update,set,condition);
       // System.out.println(this.update);

          ConnectionManager conn = new ConnectionManager();
        try{
            Connection connection = conn.getConnection();
            final PreparedStatement pr = connection.prepareStatement(this.update);
            Arrays.stream(fields).forEach(field ->{
                try {
                    pr.setObject(map.get(field.getName()),get(t,field));
                } catch (SQLException e) {
                    throw new OrmException(e.getMessage());
                }

            });
            pr.executeUpdate();
            connection.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(BaseResponsity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Optional<T> findById(ID id) {
        Optional<Field> field = Arrays
                .stream(tClass.getDeclaredFields())
                .filter(field1 -> field1.isAnnotationPresent(Id.class))
                .findFirst();
        if(!field.isPresent()){
            return Optional.empty();
        }
        this.select = this.select+" Where "+primaryColumn(tClass,field.get().getName()) +" = ?";
      //System.out.println(this.select);
        ConnectionManager conn = new ConnectionManager();
        try (Connection connection = conn.getConnection()){
            PreparedStatement ps = connection.prepareStatement(this.select);
            ps.setObject(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                    return Optional.of(mapToEntity(rs, tClass));

            }
            connection.commit();
            connection.close();
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
            throw new OrmException(exception.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public T getOne(ID id) {

        Optional<Field> field = Arrays
                .stream(tClass.getDeclaredFields())
                .filter(field1 -> field1.isAnnotationPresent(Id.class))
                .findFirst();

        if (!field.isPresent()) {
            return null;
        }

        this.select = this.select + " WHERE " + primaryColumn(tClass, field.get().getName()) + " = ?";
        ConnectionManager conn = new ConnectionManager();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(this.select);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToEntity(rs, tClass);
            }
            connection.close();
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
            throw new OrmException(exception.getMessage());
        }


        return null;
    }

   

    @Override
    public List<T> findAll() {
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(this.select);
               

                ResultSet rs = ps.executeQuery();
                List<T> ts = new ArrayList<>();
                while (rs.next()) {
                    System.out.println(rs);
                    T t = mapToEntity(rs, tClass);
                    ts.add(t);
                }
               
                return ts;
              
            } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
                throw new OrmException(exception.getMessage());
            }
        }

    @Override
    public long count() {
         ConnectionManager conn = new ConnectionManager();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(this.count);

            ResultSet rs = ps.executeQuery();

           if(rs.next()){
               return rs.getLong("total");
           }
           connection.close();
           return 0;
        } catch (SQLException exception) {
            throw new OrmException(exception.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ID id) {
        Field[] fields = tClass.getDeclaredFields();
        Optional<Field> fieldId =Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Id.class)).findFirst();
        if(fieldId.isPresent()){
            String column = primaryColumn(tClass,fieldId.get().getName());
            this.delete = this.delete + " Where "+column +" = ?;";
            
        }
      //  System.out.println(this.delete);
            ConnectionManager conn = new ConnectionManager();
            try {
                Connection connection = conn.getConnection();
                PreparedStatement ps = connection.prepareStatement(this.delete);
                ps.setObject(1,id);
                ps.executeUpdate();
                //connection.commit();
                return true;
            } catch (SQLException ex) {
                
                Logger.getLogger(BaseResponsity.class.getName()).log(Level.SEVERE, null, ex);
           } 
             //To change body of generated methods, choose Tools | Templates.
             return false;
            
    }

    @Override
    public List<T> findAllByAColumn(String column) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(this.select);
               

                ResultSet rs = ps.executeQuery();
                List<T> ts = new ArrayList<>();
                while (rs.next()) {
                    System.out.println(rs);
                    T t = mapToEntity(rs, tClass);
                    ts.add(t);
                }
               
                return ts;
              
            } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
                throw new OrmException(exception.getMessage());
            }
    }

 }

  /*  @Override
    public long count() {
        ConnectionManager conn = new ConnectionManager();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(this.count);

            ResultSet rs = ps.executeQuery();

           if(rs.next()){
               return rs.getLong("total");
           }
           return 0;
        } catch (SQLException exception) {
            throw new OrmException(exception.getMessage());
        }


    }

   /* @Override
    public boolean delete(ID id) {
        Field[] fields = tClass.getDeclaredFields();
        Optional<Field> fieldId =Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Id.class)).findFirst();
        if(fieldId.isPresent()){
            String column = primaryColumn(tClass,fieldId.get().getName());
            this.delete = this.delete+" Where "+column +" =?";
            ConnectionManager conn = new ConnectionManager();
            try {
                Connection connection = conn.getConnection();
                PreparedStatement ps = connection.prepareStatement(this.delete);
                ps.setObject(1,id);
                ps.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(BaseResponsity.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        
         }*/
    

  
  

