package com.trkj.esstaffservice.Dao;

import com.trkj.user.entity.*;
import com.trkj.user.vo.SjstzjbVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffDao {
    //查询出所有考试列表(多表查询){正式考试}
    public List<ExaminationEntity> select(String examname);
    //查询出所有考试列表(多表查询){模拟考试}
    public List<ExaminationEntity> selectmn(String examname);
    //根据考试id查询出试卷id
    public int selectsjid(int examinationid);
    //根据试卷id统计出所有试题(中间表)
    public SjstzjbVo selectsum(int testpaperid);
    //根据试卷找到试题内容
    public List<SjstzjbVo> selectstId(int testpaperid);
    //根据试题id找到试题选项
    public List<Answers> selecttmxx(int testid);
    //根据前端返回的结果去去判断是否正确
    public String selectpd(String optioncontent);
    //根据考试id修改考试状态
    public int updatezt(int examinationid);
    //添加考试记录
    public int insertjl(Record record);
    //根据考试id找到考试名称
    public String selectmc(int examinationid);
    //根据用户id查询考试记录
    public List<Record> selectrecord(int userid);
    //查询出所有的题库分类
    public List<Questionbank> selectfl();
    //查询题库分页
    public List<Questionbank> selecttk(@Param("qbname") String qbname,@Param("qbclassification") String qbclassification);
    //根据题库id统计题目类型
    public List<TquestionsEntity> selectcount(int questionbankid);
    //根据题库id或者题库分类找到试题id
    public List<TquestionsEntity> selectstid(@Param("questionbankid") int questionbankid,@Param("tqtype") String tqtype);
    //根据题库id统计试题数量
    public Questionbank selecttjst(int questionbankid);
    //根据题库id修改题库状态
    public int updatetkzt(int questionbankid);

}
