/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respository;

import java.io.Serializable;
import orm.JpaRepository;
import pojo.Answer;


/**
 *
 * @author PC
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    
}
