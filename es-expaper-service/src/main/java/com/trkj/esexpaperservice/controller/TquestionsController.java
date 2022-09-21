package com.trkj.esexpaperservice.controller;

import com.trkj.jwt.service.TquestionsService;
import com.trkj.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TquestionsController")
public class TquestionsController {
    @Autowired
    private TquestionsService tquestionsService;
    @GetMapping("/selectst")
    public AjaxResponse selectst(){
        return AjaxResponse.success(tquestionsService.selectst());
    }
}
