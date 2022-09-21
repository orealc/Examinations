package com.trkj.jwt.service;

import com.github.pagehelper.PageInfo;
import com.trkj.user.entity.ExaminationEntity;

import java.util.List;

public interface ExaminationService {
    //查询全部考试
    PageInfo<ExaminationEntity> selectks(int pageNum, int pageSize, String examname);
    //添加考试
    int insertks(ExaminationEntity examinationEntity);
}
