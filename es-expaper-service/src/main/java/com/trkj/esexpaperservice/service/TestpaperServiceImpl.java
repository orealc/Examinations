package com.trkj.esexpaperservice.service;

import com.trkj.esexpaperservice.dao.Testpaper;
import com.trkj.jwt.service.TestpaperService;
import com.trkj.user.entity.TestpaperEntity;
import com.trkj.vo.AjaxResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class TestpaperServiceImpl implements TestpaperService {
@Autowired
  private  Testpaper testpaper;

    @Override
    public List<TestpaperEntity> selectsj() {
        return testpaper.selectsj();
    }

    @Override
    public int insertsj(TestpaperEntity testpaperEntity) {
        return testpaper.insertsj(testpaperEntity);
    }

    @Override
    public int selectzh() {
        return testpaper.selectzh();
    }


}
