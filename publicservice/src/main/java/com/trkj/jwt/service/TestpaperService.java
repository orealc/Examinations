package com.trkj.jwt.service;

import com.github.pagehelper.PageInfo;
import com.trkj.user.entity.TestpaperEntity;

import java.util.List;

public interface TestpaperService {
    //查询试卷
    public List<TestpaperEntity>cxsj();
    //条件查询全部试卷
     PageInfo<TestpaperEntity>selectsj(int pageNum,int pageSize,String testname);
    //添加试卷
    int insertsj(TestpaperEntity testpaperEntity);
    //找到最后一条记录的id
    public int selectzh();
}
