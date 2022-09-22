package com.trkj.esuserservice.dao;


import com.trkj.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @Entity mapper.domain.Role
*/
@Mapper
public interface RoleMapper {
    List<Role> rolecx();

}
