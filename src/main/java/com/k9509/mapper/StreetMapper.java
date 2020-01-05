package com.k9509.mapper;

import com.k9509.domain.Street;
import com.k9509.domain.StreetExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    List<Street>selectStreet();

    @Delete("delete from street where DISTRICT_ID=#{did}")
    int deleteByDid(Integer did);

    int deleteByDidAll(Integer [] id);
}