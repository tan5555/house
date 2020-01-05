package com.k9509.userController;

import com.github.pagehelper.PageInfo;
import com.k9509.domain.*;
import com.k9509.service.*;
import com.k9509.utils.MD5Utils;
import com.k9509.utils.SerchCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 2019/12/26.
 */
@Controller
@RequestMapping("pcontroller")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;

    //注册验证用户名
    @RequestMapping("add")
    @ResponseBody
    public String add(String uname){
        boolean b = userService.findUser(uname);
        return "{\"result\":"+b+"}";
    }

    //注册
    @RequestMapping("addUser")
    public String addUser(Users users){
        userService.insertUser(users);
        return "page/login";
    }

    //登录
    @RequestMapping("guanli")
    public String main(String name, String password , Model model,HttpSession session){

        List<Users> usersList = userService.login(name);
        String pwd = MD5Utils.md5Encrypt(password);


        if (usersList.size()>0){
            if (usersList.get(0).getPassword().equals(pwd)){
                session.setAttribute("user",usersList.get(0));
                session.setMaxInactiveInterval(300);
                return "redirect:house";
            }
            model.addAttribute("msg","fail1");
        }else {
            model.addAttribute("msg","fail2");
        }
        return "page/login";
    }


    //查询发布的数据
    @RequestMapping("house")
    public String house(HttpSession session,@RequestParam(defaultValue = "1") Integer page,Model model){
        Users user = (Users) session.getAttribute("user");
        PageInfo pageInfo = houseService.findHouse(page, 5, user.getId());
        model.addAttribute("pageInfo",pageInfo);
        return "page/guanli";
    }

    //类型
    @RequestMapping("findType")
    @ResponseBody
    public List<Type> findType(){
        return typeService.findAllType();
    }

    //区域
    @RequestMapping("findDistrict")
    @ResponseBody
    public List<District> findDistrict(){
        return districtService.findAllDistrict();
    }

    //街道
    @RequestMapping("findStreet")
    @ResponseBody
    public List<Street> findStreet(Integer did){
        List<Street> street = streetService.findAllStreet(did);
        System.out.println(street);
        return streetService.findAllStreet(did);
    }

    //发布
    @RequestMapping("addFabu")
    public String addFabu(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session,Model model){
        try {
            //上传文件
            String pfileName = pfile.getOriginalFilename();
            //文件名后缀
            String sfix = pfileName.substring(pfileName.lastIndexOf("."));
            String pfix = System.currentTimeMillis()+"";

            Users user = (Users)session.getAttribute("user");
            Integer id = user.getId();
            String saveName=pfix+id+sfix;
            String savePath="H:\\java\\Java\\U04\\img\\"+saveName;
            File file=new File(savePath);
            pfile.transferTo(file);

            house.setId(pfix);
            house.setUserId(id);
            house.setPath(saveName);

            Integer i = houseService.insertHouse(house);
            if (i>0){
                model.addAttribute("insert","success");
                return "redirect:house";
            }else {
                model.addAttribute("insert","fail");
                return "page/fabu";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "page/fabu";
    }

    //退出
    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        return "page/login";
    }

    //修改回显
    @RequestMapping("update")
    public String update(String id,Model model){
        House house = houseService.findHouseById(id);
        model.addAttribute("house",house);
        return "page/update";
    }

    //修改
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String path, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile,HttpSession session,Model model){

            try {
                String pfileName = pfile.getOriginalFilename();
                Users user = (Users)session.getAttribute("user");
                Integer id = user.getId();

                String pfix = System.currentTimeMillis()+"";
                if(pfileName!=""){
                    //上传文件
                    //文件名后缀
                    String sfix = pfileName.substring(pfileName.lastIndexOf("."));

                    String saveName=pfix+id+sfix;
                    String savePath="H:\\java\\Java\\U04\\img\\"+saveName;
                    File file=new File(savePath);
                    pfile.transferTo(file);

                    house.setPath(saveName);

                    File file1=new File("H:\\java\\Java\\U04\\img\\"+path);
                    file1.delete();
                }


                Integer i = houseService.updateHouse(house);
                if (i>0){
                    model.addAttribute("update","success");
                }else {
                    model.addAttribute("update","fail");
                }
                return "redirect:house";
            } catch (IOException e) {
                e.printStackTrace();
            }

        return "redirect:login";
    }

    //删除
    @RequestMapping("delHouse")
    public String delHouse(House house,Integer state){
        house.setIsdel(state);
        Integer i = houseService.updateHouse(house);
        return "redirect:house";
    }

    //房屋查找
    @RequestMapping("list")
    public String list(SerchCondition serchCondition, @RequestParam(defaultValue = "1") Integer page,  Model model){
        serchCondition.setState(1);
        PageInfo pageInfo = houseService.findHouseByState(page, 5, serchCondition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("serchCondition",serchCondition);
        System.out.println(pageInfo);
        return "page/list";
    }


    @RequestMapping("login")
    public String login(){
        return "page/login";
    }

    @RequestMapping("regs")
    public String regs(){
        System.out.println("hh");
        return "page/regs";
    }

    @RequestMapping("fabu")
    public String fabu(){
        return "page/fabu";
    }



}
