package com.k9509.service;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.House;
import com.k9509.utils.SerchCondition;

import java.util.List;

/**
 * Created by Admin on 2019/12/28.
 */
public interface HouseService {

    Integer insertHouse(House house);

    PageInfo findHouse(Integer page,Integer rows,Integer userId);

    House findHouseById(String id);

    Integer updateHouse(House house);

    PageInfo findHouseByState(Integer page, Integer rows,SerchCondition serchCondition);

    Integer upState(House house);

    Integer someupStateS(Integer[] id);
    Integer someupStateF(Integer[] id);
}
