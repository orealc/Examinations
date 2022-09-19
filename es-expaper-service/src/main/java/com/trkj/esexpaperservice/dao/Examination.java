package com.trkj.esexpaperservice.dao;

import com.trkj.user.entity.ExaminationEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface Examination {
    public List<ExaminationEntity> selectks();
}
