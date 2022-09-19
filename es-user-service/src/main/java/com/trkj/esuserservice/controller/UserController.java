package com.trkj.esuserservice.controller;

import com.trkj.topicuserservice.service.UserService;
import com.trkj.user.vo.UserVo;
import com.trkj.view.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public AjaxResponse login(@RequestBody UserVo userVo){
        System.out.println("uservo="+userVo);
        return  AjaxResponse.success(userService.createJwtToken(userVo.getUserName(),userVo.getUserId()+""));
    }
    @GetMapping("/index")
    public AjaxResponse index(){
        System.out.println("index,............");
        return  AjaxResponse.success("welcome index.....");
    }
}
