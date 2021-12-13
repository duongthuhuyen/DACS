/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Respository.UserRepository;
import Respository.impl.UserRepositoryImpl;
import com.laptrinhjavaweb.dao.IUserDAO;
import exception.OrmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Users;
import responsity.ConnectionManager;

/**
 *
 * @author PC
 */
public class UserDao implements IUserDAO{

    @Override
    public Users findByUserNameAndPasswordAndStatus(String userName, String password) {
         List<Users> listUsers = new ArrayList<>();
        UserRepository userRepository= new UserRepositoryImpl();
        listUsers = userRepository.findAll();
        for(Users u:listUsers){
           
           if(u.getName().equals(userName)&& u.getPass().equals(password)){
               return u;
           }
        }
       return null;
       
    }

    @Override
    public int saveAcount(String userName, String password) {
        int k = 0;
        boolean t = true;
        List<Users> listUsers = new ArrayList<>();
        Users user = new Users();
        user.setName(userName);
        user.setPass(password);
        user.setIsAdmin(false);
        UserRepository userRepository= new UserRepositoryImpl();
        listUsers = userRepository.findAll();
        for(Users u:listUsers){
           
            if(u.getName().equals(user.getName())){
                t = false;
            }
        }
        if(t == true){
            userRepository.save(user);
            List<Users> ls = userRepository.findAll();
            k = ls.get(listUsers.size()-1).getId();
        }
        return k;// neu tra ve 0 thi no chua duoc them vao trong db 
        
    }

    @Override
    public List<Users> findAllUser() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Users> listUsers = new ArrayList<>();
        UserRepository userRepository= new UserRepositoryImpl();
        listUsers = userRepository.findAll();
      
        return listUsers;
    }

    @Override
    public boolean deleteByUserID(int userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       ConnectionManager conn = new ConnectionManager();
            try (Connection connection = conn.getConnection()) {
                String sql = "DELETE FROM user WHERE ID = "+userId+";";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.execute();
                return true;
              
            } catch (SQLException ex) { 
            Logger.getLogger(IinfortestImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    
}
