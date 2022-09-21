package com.trkj.esexpaperservice.service;

import com.trkj.esexpaperservice.dao.Tquestions;
import com.trkj.jwt.service.TquestionsService;
import com.trkj.user.entity.TquestionsEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class TquestionsServiceImpl implements TquestionsService {
    @Autowired
    private Tquestions tquestions;
    @Override
    public List<TquestionsEntity> selectst() {
        return tquestions.selectst();
    }
}
