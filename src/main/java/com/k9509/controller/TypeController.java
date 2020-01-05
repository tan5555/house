package com.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.Type;
import com.k9509.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    //异步数据
    @RequestMapping("type")
    @ResponseBody
    public Map<String,Object> Type(Integer page,Integer rows){
        PageInfo pageInfo = typeService.findType(page, rows);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addType")
    @ResponseBody
    public Integer addType(Type type){
        System.out.println("2");
        type.setName("五室三厅");

        try{
            Integer i = typeService.addType(type);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    @RequestMapping("EideType")
    @ResponseBody
    public Integer EideType(Type type){
        try{
            Integer i = typeService.eideType(type);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    //获取单行数据
    @RequestMapping("getType")
    @ResponseBody
    public Type getType(Integer id){
        try{
            Type type = typeService.getType(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //删除
    @RequestMapping("removeType")
    @ResponseBody
    public Integer removeType(Integer id){
        try{
            typeService.deleteType(id);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    //批量删除
    @RequestMapping("removeTypeAll")
    @ResponseBody
    public Integer removeTypeAll(Integer [] ids){
        try{
            typeService.deleteTypeAll(ids);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


}
