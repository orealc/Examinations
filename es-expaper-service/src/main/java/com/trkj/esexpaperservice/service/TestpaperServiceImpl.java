package com.trkj.esexpaperservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.dao.Testpaper;
import com.trkj.jwt.service.TestpaperService;
import com.trkj.user.entity.TestpaperEntity;
import com.trkj.util.BeanTools;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class TestpaperServiceImpl implements TestpaperService {
@Autowired
  private  Testpaper testpaper;

//查询试卷
    @Override
    public List<TestpaperEntity> cxsj() {
        return testpaper.cxsj();
    }
//分页查询试卷
    @Override
    public PageInfo<TestpaperEntity> selectsj(int pageNum, int pageSize, String testname) {
        Page<Testpaper>page= PageHelper.startPage(pageNum,pageSize);
        List<TestpaperEntity>list=testpaper.selectsj(testname);
        Page<TestpaperEntity>post=new Page<>();
        BeanTools.copyList(list,post,TestpaperEntity.class);
        PageInfo<TestpaperEntity>pageInfo=new PageInfo<>(post);
        return pageInfo;
    }
//添加试卷
    @Override
    public int insertsj(TestpaperEntity testpaperEntity) {
        return testpaper.insertsj(testpaperEntity);
    }
//查询最后生成的一条id
    @Override
    public int selectzh() {
        return testpaper.selectzh();
    }


}
