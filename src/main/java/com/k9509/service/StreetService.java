package com.k9509.service;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.Street;
import com.k9509.domain.Type;

import java.util.List;

/**
 * Created by Admin on 2019/12/27.
 */
public interface StreetService {
    PageInfo findStreet(Integer page, Integer rows);

    Integer addStreet(Street street);

    Integer eideStreet(Street street);

    Street getStreet(Integer id);

    void deleteStreet(Integer id);

    void deleteStreetAll(Integer[] ids);

    List<Street> findAllStreet(Integer did);
}
