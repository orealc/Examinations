package com.trkj.esuserservice.controller;


import com.github.pagehelper.PageInfo;
import com.trkj.esuserservice.config.ReleaseToken;
import com.trkj.esuserservice.service.DeptService;
import com.trkj.esuserservice.service.RoleService;
import com.trkj.esuserservice.service.UserServiceu;
import com.trkj.esuserservice.vo.AjaxResponse;
import com.trkj.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/usercontroller")
@RestController
@Slf4j
public class User {
    @Autowired
    private UserServiceu userServiceu;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    //注册
    @PostMapping("/insertuser")
    @ReleaseToken
    public AjaxResponse insertuser(@RequestBody UserEntity userEntity){
        System.out.println("添加完成!!!"+userEntity);
        return AjaxResponse.success(userServiceu.insertuser(userEntity));
    }
    //查询所有部门
    @GetMapping("/deptIdcx")
    @ReleaseToken
    public AjaxResponse deptIdcx(){
        System.out.println("查询成功!!!");
        return AjaxResponse.success(deptService.deptIdcx());
    }
    //多表查询用户个人信息
    @GetMapping("/usercx")
    public AjaxResponse usercx(Integer userid) {
        System.out.println("查询成功!!!");
        System.out.println("数据");
        return AjaxResponse.success(userServiceu.usercx(userid));
    }
    //查询密码是否正确
    @GetMapping("/xiugai1")
    public AjaxResponse xiugai1(Integer userid) {
        System.out.println("查询成功!!!");
        return AjaxResponse.success(userServiceu.xiugai1(userid));
    }
    //修改密码
    @GetMapping("/upxiugai")
    public AjaxResponse upxiugai(Integer userid,Integer password) {
        System.out.println("修改成功!!!");
        return AjaxResponse.success(userServiceu.upxiugai(userid,password));
    }
    //查询所有的员工信息
    @GetMapping("/usercx2")
    public AjaxResponse usercx2(int pageNum,int pageSize) {
        System.out.println("查询!!!");
        System.out.println("数据"+userServiceu.usercx2(pageNum,pageSize));
        PageInfo<UserEntity> info=userServiceu.usercx2(pageNum,pageSize);
        return AjaxResponse.success(info);
    }
    //条件查询员工信息
    @GetMapping("/usercx3")
    public AjaxResponse usercx3(String realname,String deptname) {
        System.out.println("查询!!!");
        System.out.println("数据22222222"+realname+deptname);
        return AjaxResponse.success(userServiceu.usercx3(realname,deptname));
    }
    //条件查询员工信息
    @GetMapping("/usercx4")
    public AjaxResponse usercx3(String realname) {
        System.out.println("数据realname"+realname);
        return AjaxResponse.success(userServiceu.usercx4(realname));
    }
    //查询所有的角色职称
    @GetMapping("/rolecx")
    public AjaxResponse rolecx(){
        System.out.println("查询成功!!!");
        return AjaxResponse.success(roleService.rolecx());
    }
    //修改角色职称
    @PostMapping("/xg")
    public AjaxResponse xg(@RequestBody UserEntity userEntity) {
        System.out.println("修改成功!!!"+userEntity);
        return AjaxResponse.success(userServiceu.xg(userEntity));
    }
    //修改员工部门
    @PostMapping("/xg2")
    public AjaxResponse xg2(@RequestBody UserEntity userEntity) {
        System.out.println("修改成功!!!"+userEntity);
        return AjaxResponse.success(userServiceu.xg2(userEntity));
    }
    //修改员工部门
    @PostMapping("/xg3")
    public AjaxResponse xg3(@RequestBody UserEntity userEntity) {
        System.out.println("修改成功!!!"+userEntity);
        return AjaxResponse.success(userServiceu.xg3(userEntity));
    }
}
