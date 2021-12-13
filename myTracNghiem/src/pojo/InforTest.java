/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import com.news.orm.Annotation.Column;
import com.news.orm.Annotation.Entity;
import com.news.orm.Annotation.Id;

import java.util.Date;

/**
 *
 * @author PC
 */
@Entity(name = "infortest")

public class InforTest {
    @Id(name = "ID",autoIncrement = true) 
    private int id;
    @Column(name = "UserID")
    private int userID;
    @Column(name = "Name")
    private String name;
    @Column(name = "Topic")
    private String topic;
    @Column(name = "Publish")
    private boolean publish;
    @Column(name = "DateCreate")
    private Date dateCreate;

    public InforTest(int id, int userID, String name, String topic, boolean publish, Date dateCreate) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.topic = topic;
        this.publish = publish;
        this.dateCreate = dateCreate;
    }

    public InforTest() {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public boolean isPublish() {
        return publish;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    
  }
    

