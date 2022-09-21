package com.trkj.esexpaperservice.controller;

import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.entity.Tquestions;
import com.trkj.esexpaperservice.service.TquestionsService;
import com.trkj.esexpaperservice.vo.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TquestionsController {
    @Autowired
    private TquestionsService tquestionsService;
    //条件查询所有
    @GetMapping("/eqselname")
    public AjaxResponse eqselname(String tcontent,int pageNum, int pageSize){
        System.out.println("条件查询所有");
        PageInfo<Tquestions> info = tquestionsService.eqselname(tcontent,pageNum,pageSize);
        return AjaxResponse.success(info);
    }
    //试题新增
    @PostMapping("/insst")
    public AjaxResponse insst(@RequestBody Tquestions tquestions){
        System.out.println("条件查询所有");
        return AjaxResponse.success(tquestionsService.insst(tquestions));
    }
    //根据id查询
    @GetMapping("/findid")
    public AjaxResponse findid(int testid) {
        log.debug("编号{}", testid);
        return AjaxResponse.success(tquestionsService.findid(testid));
    }
    //根据id查询
    @GetMapping("/selstname")
    public AjaxResponse selstname(int questionbankid) {
        log.debug("编号{}", questionbankid);
        return AjaxResponse.success(tquestionsService.selstname(questionbankid));
    }
    //修改
    @PutMapping("/uptquestion")
    public AjaxResponse uptquestion(@RequestBody Tquestions tquestions) {
        System.out.println("1111111111111111111"+tquestions);
        return AjaxResponse.success(tquestionsService.uptquestion(tquestions));
    }
}
