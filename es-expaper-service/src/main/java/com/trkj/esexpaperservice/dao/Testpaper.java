package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.TestpaperEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Testpaper {
    //查询试卷
    public List<TestpaperEntity> cxsj();
    //分页查询试卷
     List<TestpaperEntity> selectsj(String testname);
    //添加试卷
    int insertsj(TestpaperEntity testpaper);
    //找到最后一条记录的id
    public int selectzh();
}
