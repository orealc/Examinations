package com.trkj.esexpaperservice.controller;

import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.entity.Questionbank;
import com.trkj.esexpaperservice.service.QuestionbankService;
import com.trkj.esexpaperservice.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionbanController {
    @Autowired
    private QuestionbankService questionbankService;
    //条件查询
    @GetMapping("/selname")
    public AjaxResponse selname(String qbname,int pageNum,int pageSize){
        System.out.println("条件查询题库");
        return AjaxResponse.success(questionbankService.selname(qbname,pageNum,pageSize));
    }
//    根据id查询
    @GetMapping("/findtkid")
    public AjaxResponse findtkid(int questionbankid){
        System.out.println("根据id查询题库");
        return AjaxResponse.success(questionbankService.findtkid(questionbankid));
    }
    //修改
    @PutMapping("/uptikus")
    public AjaxResponse uptquestion(@RequestBody Questionbank questionbank) {
        System.out.println("1111111111111111111"+questionbank);
        return AjaxResponse.success(questionbankService.uptikus(questionbank));
    }
    //新增题库
    @PostMapping("/tkxz")
    public AjaxResponse insertk(@RequestBody Questionbank questionbank){
        System.out.println("新增题库");
        return AjaxResponse.success(questionbankService.tkxz(questionbank));
    }
}
