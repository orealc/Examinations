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
    UserEntity xiugai1(@Param("userid") Integer userid);
    int upxiugai(@Param("userid")Integer userid,@Param("password")Integer password);
    List<UserEntity> usercx2();
    List<UserEntity> usercx3(@Param("realname") String realname,@Param("deptname") String deptname);
    UserEntity usercx4(@Param("realname") String realname);
    int xg(UserEntity userEntity);
    int xg2(UserEntity userEntity);
    int xg3(UserEntity userEntity);
}
