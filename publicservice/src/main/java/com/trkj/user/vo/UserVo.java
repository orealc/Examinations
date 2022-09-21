package com.trkj.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private int userid;
    private String userName;
    private String phone;
    private String password;
    private String realname;
    private String token;
    private int roleid;
}
