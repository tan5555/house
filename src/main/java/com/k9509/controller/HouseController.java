package com.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.House;
import com.k9509.service.HouseService;
import com.k9509.utils.SerchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Admin on 2019/12/31.
 */
@Controller
@RequestMapping("controller")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("getAudit")
    @ResponseBody
    public Map<String,Object> house(@RequestParam(defaultValue = "1") Integer page, Integer rows, SerchCondition serchCondition){

        PageInfo pageInfo = houseService.findHouseByState(page, rows,serchCondition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //审核
    @RequestMapping("audit")
    @ResponseBody
    public String audit(House house){
        Integer i = houseService.updateHouse(house);
        return String.valueOf(i);
    }

    //批量审核
    @RequestMapping("someauditS")
    @ResponseBody
    public String someauditS(Integer[] ids){
        System.out.println(ids.toString());
        Integer i = houseService.someupStateS(ids);
        return String.valueOf(i);
    }

    @RequestMapping("someauditF")
    @ResponseBody
    public String someauditF(Integer [] ids){
        System.out.println(ids.toString());
        Integer i = houseService.someupStateS(ids);
        return String.valueOf(i);
    }
}
