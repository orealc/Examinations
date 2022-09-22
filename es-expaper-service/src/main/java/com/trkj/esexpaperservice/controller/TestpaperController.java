package com.trkj.esexpaperservice.controller;

import com.trkj.jwt.service.SjstzjbService;
import com.trkj.jwt.service.TestpaperService;
import com.trkj.user.entity.SjstzjbEntity;
import com.trkj.user.entity.TestpaperEntity;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("TestpaperController")
public class TestpaperController {
    @DubboReference
    private TestpaperService testpaperService;
    @DubboReference
    private SjstzjbService sjstzjbService;
    //查询全部试卷
    @GetMapping("/selectsj")
    public AjaxResponse selectsj(){
        System.out.println("111111111111");
        return AjaxResponse.success(testpaperService.selectsj());
    }
    //添加试卷（同时添加试卷试题中间表）
    @PostMapping("insertsj")
    public AjaxResponse insertsj(@RequestBody TestpaperEntity testpaperEntity){
        System.out.println("加进去了!");
        System.out.println("数座消息"+testpaperEntity.toString());
        int insertsj = testpaperService.insertsj(testpaperEntity);
        int selectzh = testpaperService.selectzh();//添加的试卷id
        for(int i=0;i<=testpaperEntity.getShuz().length;i++){
            int id=testpaperEntity.getShuz()[i];
            SjstzjbEntity entity=new SjstzjbEntity();
            entity.setTestpaperid(selectzh);
            entity.setTestid(id);
            sjstzjbService.insertsjstzjb(entity);
            System.out.println("添加成功");

        }
        System.out.println("添加的试卷id"+selectzh);
        return AjaxResponse.success(insertsj);

    }

}
