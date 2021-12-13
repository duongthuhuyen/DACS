/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import pojo.TestDetail;

/**
 *
 * @author PC
 */
public interface ItestDetail {
    List<TestDetail> getByTestId(int testId);
    boolean saveQuestionInTest(int testID,int quiID);
    boolean delete(int testId,int quiId);
    int countQuestionInTest(int testID);
    boolean deleteByQuidID(int quidId);
}
