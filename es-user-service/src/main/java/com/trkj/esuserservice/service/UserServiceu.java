package com.trkj.esuserservice.service;

import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserServiceu {
    UserVo login(String userName, String password);
    int insertuser(UserEntity userEntity);
    UserEntity usercx(Integer userid);
}
