package com.trkj.jwt.service;

import com.trkj.user.entity.ExaminationEntity;

import java.util.List;

public interface ExaminationService {
    //查询全部考试
    public List<ExaminationEntity> selectks();
}
