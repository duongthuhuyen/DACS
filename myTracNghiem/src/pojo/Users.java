/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import com.news.orm.Annotation.Column;
import com.news.orm.Annotation.Entity;
import com.news.orm.Annotation.Id;


/**
 *
 * @author PC
 */
@Entity(name = "user")

public class Users {
    @Id(name = "ID",autoIncrement = true)
    
    private int id;
    @Id(name = "Name",autoIncrement = false)
    private String name;
    @Column(name = "Pass")
    private String pass;
    @Column(name = "IsAdmin")
    private boolean isAdmin;
    
    
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
