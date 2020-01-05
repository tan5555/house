package com.k9509.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.k9509.domain.*;
import com.k9509.mapper.*;
import com.k9509.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 2019/12/20.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo findDistrict(Integer page,Integer rows) {
        DistrictExample districtExample=new DistrictExample();
        PageHelper.startPage(page,rows);
        List<District> districtList = districtMapper.selectByExample(districtExample);
        PageInfo pageInfo=new PageInfo(districtList);
        return pageInfo;
    }

    @Override
    public List<Street> findStreet() {
        return streetMapper.selectStreet();
    }

    @Override
    public Integer addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public Integer eideDistrict(District district) {
        return districtMapper.updateByPrimaryKey(district);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    //删除
    @Override
    @Transactional
    public void deleteDistrict(Integer id) {
        int i = districtMapper.deleteByPrimaryKey(id);
//        int p=i/0;
        int j = streetMapper.deleteByDid(id);
    }

    //批量删除
    @Override
    @Transactional
    public void deleteDistrictAll(Integer [] ids) {
        int i = districtMapper.deleteByIdAll(ids);
//        int p=i/0;
        int j = streetMapper.deleteByDidAll(ids);
    }

    @Override
    public List<District> findAllDistrict() {
        DistrictExample districtExample=new DistrictExample();
        return districtMapper.selectByExample(districtExample);
    }


}
