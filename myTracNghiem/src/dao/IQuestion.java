/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Optional;
import pojo.Question;

/**
 *
 * @author PC
 */
public interface IQuestion {
    Optional<Question> getQuestionById(int questionId);
    List<Question> getListByUserIDAndTopic(int userID,String topic);
    List<Question> getListByUserID(int userID);
    int saveQuestion(int userID,String topic,String content);
    boolean deleteQuestionByID(int Qid);
    boolean deleteQuestionByUserId(int userId);
}
