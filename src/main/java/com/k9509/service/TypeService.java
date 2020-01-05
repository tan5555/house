package com.k9509.service;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.Type;

import java.util.List;

/**
 * Created by Admin on 2019/12/20.
 */
public interface TypeService {

    PageInfo findType(Integer page, Integer rows);

    Integer addType(Type type);

    Integer eideType(Type type);

    Type getType(Integer id);

    void deleteType(Integer id);

    void deleteTypeAll(Integer[] ids);

    List<Type> findAllType();
}
