package com.trkj.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName role
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 角色名称
     */
    private String rolename;

    private static final long serialVersionUID = 1L;

}