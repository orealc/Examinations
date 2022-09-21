package com.trkj.esexpaperservice.service;

import com.trkj.esexpaperservice.dao.SjstDao;
import com.trkj.jwt.service.SjstzjbService;
import com.trkj.user.entity.SjstzjbEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class SjstzjbServiceImpl implements SjstzjbService {
    @Autowired
    private SjstDao sjstDao;
    @Override
    public int insertsjstzjb(SjstzjbEntity sjstzjbEntity){
        int insertsjstzjb=sjstDao.insertsjstzjb(sjstzjbEntity);
        return insertsjstzjb;
    }

}
