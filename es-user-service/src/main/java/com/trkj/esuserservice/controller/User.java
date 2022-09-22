package com.trkj.esuserservice.controller;


import com.trkj.esuserservice.config.ReleaseToken;
import com.trkj.esuserservice.service.DeptService;
import com.trkj.esuserservice.service.UserServiceu;
import com.trkj.esuserservice.vo.AjaxResponse;
import com.trkj.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/usercontroller")
@RestController
@Slf4j
public class User {
    @Autowired
    private UserServiceu userServiceu;
    @Autowired
    private DeptService deptService;
    //注册
    @PostMapping("/insertuser")
    @ReleaseToken
    public AjaxResponse insertuser(@RequestBody UserEntity userEntity){
        System.out.println("添加完成!!!"+userEntity);
        return AjaxResponse.success(userServiceu.insertuser(userEntity));
    }
    //查询所有部门
    @GetMapping("/deptIdcx")
    @ReleaseToken
    public AjaxResponse deptIdcx(){
        System.out.println("查询成功!!!");
        return AjaxResponse.success(deptService.deptIdcx());
    }
    //多表查询用户个人信息
    @GetMapping("/usercx")
    public AjaxResponse usercx(Integer userid) {
        System.out.println("查询成功!!!");
        System.out.println("数据");
        return AjaxResponse.success(userServiceu.usercx(userid));
    }
}
