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
@Entity(name = "answer")
public class Answer  {

    @Id(name = "ID",autoIncrement = true)
    private int id;
    @Column(name = "QuestionID")
    private int questionId;
    @Column(name = "Content")
    private String content;
    @Column(name = "IsCorrect")
    private boolean isCorrect;

    public Answer(int id, int questionId, String content, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public Answer() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    

}
