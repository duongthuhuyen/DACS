package com.laptrinhjavaweb.dao;

import java.util.List;
import pojo.Users;

public interface IUserDAO  {
	Users findByUserNameAndPasswordAndStatus(String userName, String password);
        int saveAcount(String userName,String password);
        List<Users> findAllUser();
        boolean deleteByUserID(int userId);
}
