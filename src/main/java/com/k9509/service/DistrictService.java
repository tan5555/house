package com.k9509.service;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.District;
import com.k9509.domain.Street;

import java.util.List;

/**
 * Created by Admin on 2019/12/20.
 */
public interface DistrictService {

    PageInfo findDistrict(Integer page, Integer rows);

    List<Street>findStreet();

    Integer addDistrict(District district);

    Integer eideDistrict(District district);

    District getDistrict(Integer id);

    void deleteDistrict(Integer id);

    void deleteDistrictAll(Integer [] ids);

    List<District>findAllDistrict();
}
