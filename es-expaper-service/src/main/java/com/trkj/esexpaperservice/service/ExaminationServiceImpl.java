package com.trkj.esexpaperservice.service;

import com.trkj.esexpaperservice.dao.Examination;
import com.trkj.jwt.service.ExaminationService;
import com.trkj.user.entity.ExaminationEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private Examination examination;
    @Override
    public List<ExaminationEntity> selectks() {
        return examination.selectks();
    }

    @Override
    public int insertks(ExaminationEntity examinationEntity) {
        return examination.insertks(examinationEntity);
    }
}
