package com.k9509.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9509.domain.House;
import com.k9509.domain.HouseExample;
import com.k9509.mapper.HouseMapper;
import com.k9509.service.HouseService;
import com.k9509.utils.SerchCondition;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2019/12/28.
 */
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public Integer insertHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo findHouse(Integer page , Integer rows, Integer userId) {
        PageHelper.startPage(page,rows);
        List<House> houseList = houseMapper.selectHouse(userId);
        PageInfo pageInfo=new PageInfo(houseList);
        System.out.println(houseList);
        return pageInfo;
    }

    @Override
    public House findHouseById(String id) {
        return houseMapper.selectById(id);
    }

    @Override
    public Integer updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo findHouseByState(Integer page, Integer rows ,SerchCondition serchCondition) {
        PageHelper.startPage(page,rows);
        List<House> houseList = houseMapper.selectHouseByState(serchCondition);
        PageInfo pageInfo=new PageInfo(houseList);
        return pageInfo;
    }

    public Integer upState(House house){
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public Integer someupStateS(Integer[] id) {
        return houseMapper.someupStateS(id);
    }

    @Override
    public Integer someupStateF(Integer[] id) {
        return houseMapper.someupStateF(id);
    }
}
