package com.trkj.esexpaperservice.controller;

import com.trkj.jwt.service.ExaminationService;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/expapaer")
@RestController
public class ExaminationController {
    @DubboReference
    private ExaminationService examinationService;
    @GetMapping("/selectks")
    public AjaxResponse selectks(){
        return AjaxResponse.success(examinationService.selectks());
    }
}
