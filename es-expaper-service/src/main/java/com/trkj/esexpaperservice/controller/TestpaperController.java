package com.trkj.esexpaperservice.controller;

import com.trkj.jwt.service.TestpaperService;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("TestpaperController")
public class TestpaperController {
    @DubboReference
    private TestpaperService testpaperService;
    //查询全部试卷
    @GetMapping("/selectsj")
    public AjaxResponse selectsj(){
        return AjaxResponse.success(testpaperService.selectsj());
    }
}
