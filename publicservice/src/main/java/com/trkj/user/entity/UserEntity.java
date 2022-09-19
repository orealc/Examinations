package com.trkj.user.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private int userId;
    private String userName;
    private String phone;
    private String email;
    private Date createTime;
    private String sex;
    private String password;
    private int userNumber;
}
