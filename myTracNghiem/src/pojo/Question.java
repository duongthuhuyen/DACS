/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import com.news.orm.Annotation.Column;
import com.news.orm.Annotation.Entity;
import com.news.orm.Annotation.Id;

 
@Entity(name = "question")

public class Question {
    @Id(name = "ID",autoIncrement = true)
    
    private int id;
    @Column(name = "UserID")
    private int userId;
    @Column(name = "Topic")
    private String topic;
    @Column(name = "Content")
    private String content;

    public Question(int id, int userId, String topic, String content) {
        this.id = id;
        this.userId = userId;
        this.topic = topic;
        this.content = content;
    }

    public Question() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
   
   
    

    
}
