package com.k9509.mapper;

import com.k9509.domain.House;
import com.k9509.domain.HouseExample;
import com.k9509.utils.SerchCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> selectHouse(Integer userId);

    House selectById(String id);

    List<House> selectHouseByState(SerchCondition serchCondition);

    int someupStateS(Integer[] id);
    int someupStateF(Integer[] id);
}