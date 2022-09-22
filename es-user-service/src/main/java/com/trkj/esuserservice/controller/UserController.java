package com.trkj.esuserservice.controller;

import com.trkj.esuserservice.config.ReleaseToken;
import com.trkj.esuserservice.service.UserService;
import com.trkj.esuserservice.service.UserServiceu;
import com.trkj.jwt.service.Jwtservice;
import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import com.trkj.vo.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceu userServiceu;
    @DubboReference
    private Jwtservice jwtService;
    //登录
    @GetMapping("/login")
    @ReleaseToken
    public AjaxResponse login(String userName ,String password){
        UserVo userVo=userServiceu.login(userName,password);
        System.out.println(userVo+"_____________"+userVo.getUserid());
        if(userVo.getUserid()!=0){
            String token=jwtService.generateToken(userName,password);
            System.out.println(token+"token");
            userVo.setToken(token);
        }else {
            new Exception();
            log.debug("数据+",userName,password);
        }
        System.out.println(userVo+"aaaaaaaaaaaaaa");
        return AjaxResponse.success(userVo);

    }
    //测试
    @GetMapping("/index")
    public AjaxResponse index(){
        System.out.println("index,............");
        return  AjaxResponse.success("welcome index.....");
    }
}
