package com.k9509.service.Impl;

import com.k9509.domain.Users;
import com.k9509.domain.UsersExample;
import com.k9509.mapper.UsersMapper;
import com.k9509.service.UserService;
import com.k9509.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2019/12/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;



    //检查用户名
    @Override
    public boolean findUser(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);

        List<Users> users = usersMapper.selectByExample(usersExample);
        System.out.println(users);
        if (users.size()>0 ){
            return false;
        }
        return true;
    }

    //注册
    @Override
    public Integer insertUser(Users users) {
        String pwd = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(pwd);
        return usersMapper.insertSelective(users);
    }

    //登录
    @Override
    public List<Users> login(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        return usersMapper.selectByExample(usersExample);

    }






}
