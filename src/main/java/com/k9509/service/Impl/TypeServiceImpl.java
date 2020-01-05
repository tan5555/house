package com.k9509.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9509.domain.*;
import com.k9509.mapper.*;
import com.k9509.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 2019/12/20.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo findType(Integer page,Integer rows) {
        TypeExample typeExample=new TypeExample();
        PageHelper.startPage(page,rows);
        List<Type> typeList = typeMapper.selectByExample(typeExample);

        PageInfo pageInfo=new PageInfo(typeList);
        return pageInfo;
    }


    @Override
    public Integer addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Integer eideType(Type type) {
        return typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    //删除
    @Override
    @Transactional
    public void deleteType(Integer id) {
        int i = typeMapper.deleteByPrimaryKey(id);
    }

    //批量删除
    @Override
    @Transactional
    public void deleteTypeAll(Integer [] ids) {
        int i = typeMapper.deleteByIdAll(ids);
    }

    @Override
    public List<Type> findAllType() {
        TypeExample typeExample=new TypeExample();
        return typeMapper.selectByExample(typeExample);
    }


}
