package com.trkj.esuserservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esuserservice.dao.UserDao;
import com.trkj.esuserservice.util.BeanTools;
import com.trkj.user.entity.UserEntity;
import com.trkj.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
@Slf4j
public class UserServiceuImpl implements UserServiceu {
    @Autowired
    private UserDao userDao;

    //登录账号
    @Override
    public UserVo login(String userName, String password) {
        UserVo login=userDao.login(userName,password);
        return login;
    }
    //注册账号
    @Override
    public int insertuser(UserEntity userEntity) {
        int insertuser=userDao.insertuser( userEntity);
        return insertuser;
    }
    //查出自己的个人信息
    @Override
    public UserEntity usercx(Integer userid) {
        UserEntity usercx=userDao.usercx(userid);
        return usercx;
    }
    //根据id查询密码是否正确
    @Override
    public UserEntity xiugai1(Integer userid) {
        UserEntity xiugai1=userDao.xiugai1(userid);
        return xiugai1;
    }
    //根据条件修改
    @Override
    public int upxiugai(Integer userid,Integer password) {
        int upxiugai=userDao.upxiugai(userid,password);
        return upxiugai;
    }
    //多表查询所有的员工信息
    @Override
    public PageInfo<UserEntity> usercx2(int pageNum, int pageSize) {
        Page<UserEntity> page= PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> list=userDao.usercx2();
        Page<UserEntity> productVos=new Page<>();
        BeanTools.copyList(list,productVos,UserEntity.class);
        PageInfo<UserEntity> pageInfo=new PageInfo<>(productVos);
        System.out.println(pageInfo);
        return pageInfo;
    }
    //根据条件查询员工数据
    @Override
    public  List<UserEntity> usercx3(String realname,String deptname) {
        List<UserEntity> usercx3=userDao.usercx3(realname, deptname);
        System.out.println("22222222222"+realname+"zzzzzz"+deptname);
        return usercx3;
    }
    //根据姓名查询
    @Override
    public UserEntity usercx4(String realname) {
        UserEntity usercx4=userDao.usercx4(realname);
        return usercx4;
    }
    //根据姓名修改
    @Override
    public int xg(UserEntity userEntity) {
        int xg=userDao.xg(userEntity);
        System.out.println("修改的数据"+userEntity);
        return xg;
    }
    //修改部门
    @Override
    public int xg2(UserEntity userEntity) {
        int xg2=userDao.xg2(userEntity);
        System.out.println("修改的数据"+userEntity);
        return xg2;
    }

    @Override
    public int xg3(UserEntity userEntity) {
        int xg3=userDao.xg3(userEntity);
        return xg3;
    }
}
