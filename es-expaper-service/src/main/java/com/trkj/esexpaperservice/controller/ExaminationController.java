package com.trkj.esexpaperservice.controller;

import com.trkj.jwt.service.ExaminationService;
import com.trkj.user.entity.ExaminationEntity;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/expapaer")
@RestController
public class ExaminationController {
    @DubboReference
    private ExaminationService examinationService;
    //查询全部考试
    @GetMapping("/selectks")
    public AjaxResponse selectks(){
        return AjaxResponse.success(examinationService.selectks());
    }
    //添加考试
    @PostMapping("/insertks")
    public AjaxResponse insertks(@RequestBody ExaminationEntity examinationEntity){
        return AjaxResponse.success(examinationService.insertks(examinationEntity));
    }
}
