package com.trkj.esexpaperservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.dao.Examination;
import com.trkj.jwt.service.ExaminationService;
import com.trkj.user.entity.ExaminationEntity;
import com.trkj.util.BeanTools;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private Examination examination;

    @Override
    public PageInfo<ExaminationEntity> selectks(int pageNum, int pageSize, String examname) {
        Page<Examination> page= PageHelper.startPage(pageNum,pageSize);
        List<ExaminationEntity>list=examination.selectks(examname);
        Page<ExaminationEntity>post=new Page<>();
        BeanTools.copyList(list,post,ExaminationEntity.class);
        PageInfo<ExaminationEntity>pageInfo=new PageInfo<>(post);
        return pageInfo;
    }

    @Override
    public int insertks(ExaminationEntity examinationEntity) {
        return examination.insertks(examinationEntity);
    }
}
