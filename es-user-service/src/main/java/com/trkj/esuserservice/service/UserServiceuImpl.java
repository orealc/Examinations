package com.trkj.esuserservice.service;

import com.trkj.esuserservice.dao.UserDao;
import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UserServiceuImpl implements UserServiceu {
    @Autowired
    private UserDao userDao;
    @Override
    public UserVo login(String userName, String password) {
        UserVo login=userDao.login(userName,password);
        return login;
    }

    @Override
    public int insertuser(UserEntity userEntity) {
        int insertuser=userDao.insertuser( userEntity);
        return insertuser;
    }

    @Override
    public UserEntity usercx(Integer userid) {
        UserEntity usercx=userDao.usercx(userid);
        return usercx;
    }
}
