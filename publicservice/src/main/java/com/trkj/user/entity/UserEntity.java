package com.trkj.user.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Integer userid;
    private int deptid;
    private String userName;
    private int phone;
    private String realname;
    private int password;
    private int roleid;

    private String deptname;

    private String rolename;
}
