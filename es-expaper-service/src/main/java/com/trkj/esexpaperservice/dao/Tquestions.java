package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.TquestionsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Tquestions {
    public List<TquestionsEntity> selectst();
}
