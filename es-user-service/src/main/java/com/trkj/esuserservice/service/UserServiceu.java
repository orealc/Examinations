package com.trkj.esuserservice.service;

import com.github.pagehelper.PageInfo;
import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserServiceu {
    //登录账号
    UserVo login(String userName, String password);
    //注册账号
    int insertuser(UserEntity userEntity);
    //查出自己的个人信息
    UserEntity usercx(Integer userid);
    //根据id查询密码是否正确
    UserEntity xiugai1(Integer userid);
    //根据条件修改
    int upxiugai(Integer userid,Integer password);
    //多表查询所有的员工信息
    PageInfo<UserEntity> usercx2(int pageNum, int pageSize);
    //根据条件查询员工数据
    List<UserEntity> usercx3(String realname,String deptname);
    //查出自己的个人信息
    UserEntity usercx4(String realname);
    //修改角色
    int xg(UserEntity userEntity);
    //修改部门
    int xg2(UserEntity userEntity);
    //员工修改信息
    int xg3(UserEntity userEntity);
}
