/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Respository.TestDetailRepository;
import Respository.impl.TestDetailRepositoryImpl;
import com.news.orm.impl.BaseResponsity;
import dao.ItestDetail;
import exception.OrmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.InforTest;
import pojo.TestDetail;
import responsity.ConnectionManager;

/**
 *
 * @author PC
 */
public class ItestDetailImpl implements ItestDetail{


    public List<TestDetail> getByTestId(int testId) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          List<TestDetail> listTestDetail = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM `testdetail` WHERE TestID = "+testId+";";
                PreparedStatement ps = connection.prepareStatement(sql);
               

                ResultSet rs = ps.executeQuery();
                List<TestDetail> ts = new ArrayList<>();
                while (rs.next()) {
                  //  System.out.println(rs);
                   TestDetail in = new TestDetail(rs.getInt("TestID"),
                   rs.getInt("QuestionID"));
                    ts.add(in);
                }
               
                return ts;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public boolean saveQuestionInTest(int testID, int quiID) {
        boolean tr = true; 
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TestDetailRepository te = new TestDetailRepositoryImpl();
        TestDetail test = new TestDetail(testID,quiID);
        List<TestDetail> list = this.getByTestId(testID);
        for(TestDetail t:list){
            if(t.getQuestionId() == quiID){
                tr = false;
                return false;
            }
        }
        if(tr){
            te.save(test);
        }
        return true;
    }

    @Override
    public boolean delete(int testId, int quiId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         ConnectionManager conn = new ConnectionManager();
            try {
                Connection connection = conn.getConnection();
                String sql = "DELETE FROM `testdetail` WHERE TestID = "+testId+" and QuestionID = "+quiId+";";
                PreparedStatement ps = connection.prepareStatement(sql);
               // ps.setObject(1,id);
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
    public int countQuestionInTest(int testID) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ConnectionManager conn = new ConnectionManager();
        try (Connection connection = conn.getConnection()) {
            String sql = "SELECT COUNT(1) as total FROM `testdetail` where TestID = "+testID;
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

           if(rs.next()){
               return rs.getInt("total");
           }
           connection.close();
           return 0;
        } catch (SQLException exception) {
            throw new OrmException(exception.getMessage());
        } //To change 
    }

    @Override
    public boolean deleteByQuidID(int qid) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "DELETE FROM `testdetail` WHERE QuestionID = "+qid+";";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.execute();
                return true;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    
}
