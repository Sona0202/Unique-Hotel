package com.dailycodework.uniquehotel.service;


import com.dailycodework.uniquehotel.model.Users;

import java.util.List;


public interface IUserService {
    Users registerUser(Users user);
    List<Users> getUsers();
    void deleteUser(String email);
    Users getUser(String email);
}