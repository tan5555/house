package com.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.Street;
import com.k9509.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2019/12/20.
 */
@Controller
@RequestMapping("controller")
public class StreetController {

    @Autowired
    private StreetService streetService;

    //异步数据
    @RequestMapping("street")
    @ResponseBody
    public Map<String,Object> Street(Integer page,Integer rows){
        PageInfo pageInfo = streetService.findStreet(page, rows);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addStreet")
    @ResponseBody
    public Integer addStreet(Street street){

        try{
            Integer i = streetService.addStreet(street);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    @RequestMapping("EideStreet")
    @ResponseBody
    public Integer EideStreet(Street street){
        try{
            Integer i = streetService.eideStreet(street);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    //获取单行数据
    @RequestMapping("getStreet")
    @ResponseBody
    public Street getStreet(Integer id){
        try{
            Street street = streetService.getStreet(id);
            return street;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //删除
    @RequestMapping("removeStreet")
    @ResponseBody
    public Integer removeStreet(Integer id){
        try{
            streetService.deleteStreet(id);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    //批量删除
    @RequestMapping("removeStreetAll")
    @ResponseBody
    public Integer removeStreetAll(Integer [] ids){
        System.out.println(ids.toString());
        try{
            streetService.deleteStreetAll(ids);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


}
