package com.news.Utils;

import exception.OrmException;
import com.news.orm.Annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import static com.news.Utils.AnnotationUtil.*;

public class ReflectionUtil {
    private ReflectionUtil(){

    }
    public static <T> Object get(Object instance, Field field){
        String name = field.getName();
        String prefix;
        if(field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)){
            prefix = "is";
        }else{
            prefix = "get";
        }
        name = prefix+name.substring(0,1).toUpperCase()+name.substring(1);
       
        try {
            Method method = instance.getClass().getDeclaredMethod(name);
            return method.invoke(instance);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
           return null;
        }
    }
    public static <T> void set(Field field,T instance,Object value) {
        String name = field.getName();
        String prefix = "set";
        name = prefix+name.substring(0,1).toUpperCase()+name.substring(1);
        //System.out.println(name);
        try {
            Method method = instance.getClass().getDeclaredMethod(name,field.getType());
           // System.out.println(field.getType());
            method.invoke(instance,value);
            //System.out.println(value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new OrmException(e.getMessage());
        }

    }
    public static <T> T mapToEntity(ResultSet rs,Class<T> tclass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final T t = tclass.getDeclaredConstructor().newInstance();
        Field[] fields = tclass.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            String columnName;
            if(!field.isAnnotationPresent(Id.class)){
                
                columnName = getColumnName(tclass,field.getName());

            }else{
               columnName = primaryColumn(tclass,field.getName());

            }
           
            try {
                //System.out.println(columnName);         
                set(field,t,rs.getObject(columnName));
               // System.out.println(rs.getObject(columnName));
                
            } catch (SQLException e) {
                throw new OrmException(e.getMessage());
            }
        });
        return t;
    }
}
