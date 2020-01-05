package com.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.District;
import com.k9509.domain.Street;
import com.k9509.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/20.
 */
@Controller
@RequestMapping("controller")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    //异步数据
    @RequestMapping("district")
    @ResponseBody
    public Map<String,Object> district(Integer page,Integer rows){
        PageInfo pageInfo = districtService.findDistrict(page, rows);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addDistrict")
    @ResponseBody
    public Integer addDistrict(District district){
        try{
            Integer i = districtService.addDistrict(district);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    @RequestMapping("EideDistrict")
    @ResponseBody
    public Integer EideDistrict(District district){
        try{
            Integer i = districtService.eideDistrict(district);
            return i;
        }catch (Exception e){
            return -1;
        }
    }

    //获取单行数据
    @RequestMapping("getDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        try{
            District district = districtService.getDistrict(id);
            return district;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //删除
    @RequestMapping("removeDistrict")
    @ResponseBody
    public Integer removeDistrict(Integer id){
        try{
            districtService.deleteDistrict(id);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    //批量删除
    @RequestMapping("removeDistrictAll")
    @ResponseBody
    public Integer removeDistrictAll(Integer [] ids){
        try{
            districtService.deleteDistrictAll(ids);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }



/*    @RequestMapping("street")
    @ResponseBody
    public List<Street> street(){
        List<Street> streetList = districtService.findStreet();
        System.out.println(streetList);
        return streetList;
    }*/

    //转发到相关页面
    @RequestMapping("admin")
    public String adminjsp(){
        return "pages/admin";
    }

    @RequestMapping("districtjsp")
    public String districtjsp(){
        return "pages/district";
    }

    @RequestMapping("streetjsp")
    public String streetjsp(){
        return "pages/street";
    }

    @RequestMapping("typejsp")
    public String typejsp(){
        return "pages/type";
    }

    @RequestMapping("housejsp")
    public String housejsp(){
        return "pages/house";
    }

    @RequestMapping("usersjsp")
    public String usersjsp(){
        return "pages/users";
    }

    @RequestMapping("stumanjsp")
    public String stumanjsp(){
        return "pages/stuman";
    }

    @RequestMapping("auditjsp")
    public String auditjsp(){
        return "pages/audit";
    }

}
