/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import com.news.orm.Annotation.Column;
import com.news.orm.Annotation.Entity;
import com.news.orm.Annotation.Id;
import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author PC
 */
@Entity(name = "history")

public class History {
    @Id(name = "ID",autoIncrement = true)
    
    private int id;
    @Column(name = "UserID")
    private int userId;
    @Column(name = "TestID")
    private int testId;
    @Column(name = "DateFinish")
    private Date dateFinish;
    @Column(name = "Point")
    private float point;

    public History(int id, int userId, int testId, Date dateFinish, float point) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.dateFinish = dateFinish;
        this.point = point;
    }

    public History() {
    }
    
    
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public float getPoint() {
        return point;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
    
   
    
}
