/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import pojo.InforTest;

/**
 *
 * @author PC
 */
public interface IInfortest {
    List<InforTest> getListByUserId(int userId);
    List<InforTest> getListOutOfUserId(int userId);
    InforTest getInforTestByTestID(int testID);
    int saveInforTest(int userId,String name,String topic,boolean publish);
    boolean deleteByUserId(int userID);
}
