package com.trkj.jwt.service;

import com.github.pagehelper.PageInfo;
import com.trkj.user.entity.*;
import com.trkj.user.vo.SjstzjbVo;

import java.util.List;

public interface StaffService {
    //查询出所有的考试列表
    public PageInfo<ExaminationEntity> select(int pageNum, int pageSize,String examname);
    //查询出所有考试列表(多表查询){模拟考试}
    public PageInfo<ExaminationEntity> selectmn(int pageNum, int pageSize,String examname);
    //根据考试id查询出试卷id
    public int selectsjid(int examinationid);
    //根据试卷id统计出所有试题(中间表【试卷id通过方法查询出来】)
    public SjstzjbVo selectsum(int testpaperid);
    //根据试卷找到试题id
    public List<SjstzjbVo> selectstId(int testpaperid);
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
    public PageInfo<Questionbank> selecttk( int pageNum, int pageSize,String qbname,String qbclassification);
    //根据题库id统计题目类型
    public List<TquestionsEntity> selectcount(int questionbankid);
    //根据题库id找到试题id
    public List<TquestionsEntity> selectstid(int questionbankid,String tqtype);
    //根据题库id统计试题数量
    public Questionbank selecttjst(int questionbankid);
    //根据题库id修改题库状态
    public int updatetkzt(int questionbankid);

}
