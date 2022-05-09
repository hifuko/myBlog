package com.ivana.service;

import com.ivana.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
