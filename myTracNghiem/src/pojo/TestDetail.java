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
@Entity(name = "testDetail")

public class TestDetail {
  //  @Id(name = "TestId",autoIncrement = true)
   @Column(name = "TestId")
    private int testId;
    @Column(name = "QuestionID")
    private int questionId;

    public TestDetail(int testId, int questionId) {
        this.testId = testId;
        this.questionId = questionId;
    }
    
    

    public int getTestId() {
        return testId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
}
