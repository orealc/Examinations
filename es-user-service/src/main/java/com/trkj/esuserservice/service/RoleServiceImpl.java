package com.trkj.esuserservice.service;


import com.trkj.esuserservice.dao.RoleMapper;
import com.trkj.user.entity.Dept;
import com.trkj.user.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
*/
@DubboService
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> rolecx() {
        List<Role> rolecx=roleMapper.rolecx();
        return rolecx;
    }
}
