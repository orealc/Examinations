package com.trkj.jwt.service;

import com.trkj.user.entity.TestpaperEntity;

import java.util.List;

public interface TestpaperService {
    //查询全部试卷
    public List<TestpaperEntity>selectsj();
    //添加试卷
    int insertsj(TestpaperEntity testpaperEntity);
    //找到最后一条记录的id
    public int selectzh();
}
