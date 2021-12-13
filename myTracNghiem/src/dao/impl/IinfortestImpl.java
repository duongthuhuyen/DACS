/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Respository.InforTestRepository;
import Respository.QuestionRepository;
import Respository.UserRepository;
import Respository.impl.InforTestRepositoryImpl;
import Respository.impl.QuestionRepositoryImpl;
import Respository.impl.UserRepositoryImpl;
import dao.IInfortest;
import exception.OrmException;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.InforTest;
import pojo.Question;
import pojo.Users;
import responsity.ConnectionManager;

/**
 *
 * @author PC
 */
public class IinfortestImpl implements IInfortest{

    public List<InforTest> getListByUserId(int userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<InforTest> listInforTest = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM `infortest` WHERE UserID = "+userId+";";
                PreparedStatement ps = connection.prepareStatement(sql);
               

                ResultSet rs = ps.executeQuery();
                List<InforTest> ts = new ArrayList<>();
                while (rs.next()) {
                  //  System.out.println(rs);
                   InforTest in = new InforTest(rs.getInt("ID"),
                   rs.getInt("UserID"),
                   rs.getString("Name"),
                   rs.getString("Topic"),
                   rs.getBoolean("Publish"), (Date) rs.getObject("DateCreate"));
                    ts.add(in);
                }
               
                return ts;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public InforTest getInforTestByTestID(int testID) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        InforTestRepository qu = new InforTestRepositoryImpl();
        InforTest q = qu.getOne(testID);
        return q;
    }

    @Override
    public int saveInforTest(int userId, String name, String topic, boolean publish) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int k = 0;
        //boolean t = true;
       // List<Users> listUsers = new ArrayList<>();
        InforTest inforTest = new InforTest();
        inforTest.setUserID(userId);
        inforTest.setName(name);
        inforTest.setPublish(publish);
        inforTest.setTopic(topic);
        Date date=java.util.Calendar.getInstance().getTime();
        inforTest.setDateCreate(date);
        
        InforTestRepository inforTestRepository= new InforTestRepositoryImpl();
        List<InforTest> listInforTests = new ArrayList<>();
        
        //listInforTests = inforTestRepository.findAll();
        inforTestRepository.save(inforTest);
        listInforTests = inforTestRepository.findAll();
        k = listInforTests.get(listInforTests.size()-1).getId();
        
        return k;
    }

    @Override
    public List<InforTest> getListOutOfUserId(int userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         List<InforTest> listInforTest = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM `infortest` WHERE UserID != "+userId+" and Publish = true;";
                PreparedStatement ps = connection.prepareStatement(sql);
               

                ResultSet rs = ps.executeQuery();
                List<InforTest> ts = new ArrayList<>();
                while (rs.next()) {
                  //  System.out.println(rs);
                   InforTest in = new InforTest(rs.getInt("ID"),
                   rs.getInt("UserID"),
                   rs.getString("Name"),
                   rs.getString("Topic"),
                   rs.getBoolean("Publish"), (Date) rs.getObject("DateCreate"));
                    ts.add(in);
                }
               
                return ts;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public boolean deleteByUserId(int userID) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "DELETE FROM `infortest` WHERE UserID = "+userID+";";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.execute();
                return true;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
}
