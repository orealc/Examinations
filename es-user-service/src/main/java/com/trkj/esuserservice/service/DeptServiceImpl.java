package com.trkj.esuserservice.service;


import com.trkj.esuserservice.dao.DeptDao;
import com.trkj.user.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
*/
@DubboService
@Slf4j
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptDao deptDao;
    @Override
    public List<Dept> deptIdcx() {
        List<Dept> deptIdcx=deptDao.deptIdcx();
        return deptIdcx;
    }
}
