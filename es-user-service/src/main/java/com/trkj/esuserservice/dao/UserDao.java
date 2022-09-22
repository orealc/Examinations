package com.trkj.esuserservice.dao;

import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    UserVo login(@Param("userName") String userName, @Param("password")  String password);
    int insertuser(UserEntity userEntity);
    UserEntity usercx(@Param("userid") Integer userid);
}
