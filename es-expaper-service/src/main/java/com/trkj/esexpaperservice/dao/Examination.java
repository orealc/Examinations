package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.ExaminationEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface Examination {
    //查询考试
    public List<ExaminationEntity> selectks();
    //添加考试
    int insertks(ExaminationEntity examinationEntity);
}
