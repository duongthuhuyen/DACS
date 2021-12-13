/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Respository;

import orm.JpaRepository;
import pojo.Users;

/**
 *
 * @author PC
 */
public interface UserRepository extends JpaRepository<Users,Long>{
    
}
