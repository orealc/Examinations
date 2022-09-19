package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.TestpaperEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Testpaper {
    public List<TestpaperEntity> selectsj();
}
