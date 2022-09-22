package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.SjstzjbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SjstDao {
//添加试卷试题中间表
    int insertsjstzjb(SjstzjbEntity sjstzjbEntity);
}
