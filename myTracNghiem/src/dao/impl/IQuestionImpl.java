/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Respository.QuestionRepository;
import Respository.UserRepository;
import Respository.impl.QuestionRepositoryImpl;
import Respository.impl.UserRepositoryImpl;
import dao.IQuestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Answer;
import pojo.InforTest;
import pojo.Question;
import pojo.Users;
import responsity.ConnectionManager;

/**
 *
 * @author PC
 */
public class IQuestionImpl implements IQuestion{

    @Override
    public Optional<Question> getQuestionById(int questionId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        QuestionRepository qu = new QuestionRepositoryImpl();
        Optional<Question> q = qu.findById(questionId);
        return q;
    }

    public List<Question> getListByUserIDAndTopic(int userID, String topic) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         List<Question> listQuestions = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM `question` WHERE UserID = "+userID+" and Topic = '"+topic+"';";
                PreparedStatement ps = connection.prepareStatement(sql);
               

                ResultSet rs = ps.executeQuery();
                List<Question> ts = new ArrayList<>();
                while (rs.next()) {
                  //  System.out.println(rs);
                   Question in = new Question(rs.getInt("ID"),
                   rs.getInt("UserID"),
                   rs.getString("Topic"),  
                   rs.getString("Content"));
                    ts.add(in);
                }
               
                return ts;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public int saveQuestion(int userID, String topic, String content) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          int k = 0;
        boolean t = true;
        List<Question> listQs = new ArrayList<>();
        Question q = new Question();
        q.setUserId(userID);
        q.setTopic(topic);
        q.setContent(content);
        QuestionRepository qestionRepository = new QuestionRepositoryImpl();
        
        listQs = qestionRepository.findAll();
        for(Question u:listQs){
           
            if(u.getContent().equals(q.getContent())){
                t = false;
            }
        }
        if(t == true){
            qestionRepository.save(q);
            List<Question> lq = qestionRepository.findAll();
            k = lq.get(lq.size()-1).getId();
        }
        return k;// neu tra ve 0 thi no chua duoc them vao trong db 
        
    }

    @Override
    public List<Question> getListByUserID(int userID) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         List<Question> listQs = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM `question` WHERE UserID = "+userID+";";
                PreparedStatement ps = connection.prepareStatement(sql);
               

                ResultSet rs = ps.executeQuery();
                List<Question> ts = new ArrayList<>();
                while (rs.next()) {
                  //  System.out.println(rs);
                   Question in = new Question(rs.getInt("ID"),
                   rs.getInt("UserID"),
                   rs.getString("Topic"),
                   rs.getString("Content"));
                    ts.add(in);
                }
               
                return ts;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    @Override
    public boolean deleteQuestionByID(int Qid) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      QuestionRepository q = new QuestionRepositoryImpl();
      boolean t = q.delete(Qid);
      return t;
    }

    @Override
    public boolean deleteQuestionByUserId(int userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "DELETE FROM `question` WHERE UserID = "+userId+";";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.execute();
                return true;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
}
