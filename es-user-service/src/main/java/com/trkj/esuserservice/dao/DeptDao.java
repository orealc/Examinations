package com.trkj.esuserservice.dao;

import com.trkj.user.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {
    List<Dept> deptIdcx();
}
