/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respository;

import orm.JpaRepository;
import pojo.Question;

/**
 *
 * @author PC
 */
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
}
