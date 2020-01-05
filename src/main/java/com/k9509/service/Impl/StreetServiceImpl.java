package com.k9509.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9509.domain.Street;
import com.k9509.domain.StreetExample;
import com.k9509.mapper.StreetMapper;
import com.k9509.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 2019/12/20.
 */
@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo findStreet(Integer page,Integer rows) {
        StreetExample streetExample=new StreetExample();
        PageHelper.startPage(page,rows);
        List<Street> streetList = streetMapper.selectByExample(streetExample);

        PageInfo pageInfo=new PageInfo(streetList);
        return pageInfo;
    }


    @Override
    public Integer addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public Integer eideStreet(Street street) {
        return streetMapper.updateByPrimaryKey(street);
    }

    @Override
    public Street getStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    //删除
    @Override
    @Transactional
    public void deleteStreet(Integer id) {
        int i = streetMapper.deleteByPrimaryKey(id);
    }

    //批量删除
    @Override
    @Transactional
    public void deleteStreetAll(Integer [] ids) {
        int i = streetMapper.deleteByDidAll(ids);
    }

    @Override
    public List<Street> findAllStreet(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }


}
