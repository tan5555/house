package com.k9509.service;

import com.k9509.domain.Users;

import java.util.List;

/**
 * Created by Admin on 2019/12/26.
 */
public interface UserService {

    boolean findUser(String name);

    Integer insertUser(Users users);

    List<Users> login(String name);


}
