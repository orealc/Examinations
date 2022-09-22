package com.trkj.esstaffservice.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esstaffservice.Dao.StaffDao;
import com.trkj.esstaffservice.util.BeanTools;
import com.trkj.jwt.service.StaffService;
import com.trkj.user.entity.*;
import com.trkj.user.vo.SjstzjbVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@DubboService
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Override
    public PageInfo<ExaminationEntity> select(int pageNum, int pageSize,String examname) {
        Page<ExaminationEntity> page= PageHelper.startPage(pageNum,pageSize);
        List<ExaminationEntity> list=staffDao.select(examname);
        Page<ExaminationEntity>address=new Page<>();
        BeanTools.copyList(list,address,ExaminationEntity.class);
        PageInfo<ExaminationEntity>pageInfo=new PageInfo<>(address);
        return pageInfo;
    }

    @Override
    public PageInfo<ExaminationEntity> selectmn(int pageNum, int pageSize, String examname) {
        Page<ExaminationEntity> page= PageHelper.startPage(pageNum,pageSize);
        List<ExaminationEntity> list=staffDao.selectmn(examname);
        Page<ExaminationEntity>address=new Page<>();
        BeanTools.copyList(list,address,ExaminationEntity.class);
        PageInfo<ExaminationEntity>pageInfo=new PageInfo<>(address);
        return pageInfo;
    }

    @Override
    public int selectsjid(int examinationid) {
        return staffDao.selectsjid(examinationid);
    }

    @Override
    public SjstzjbVo selectsum(int testpaperid) {
        return staffDao.selectsum(testpaperid);
    }

    @Override
    public List<SjstzjbVo> selectstId(int testpaperid) {
        //根据试卷id查询出对应的试题id
        List<SjstzjbVo> sjstzjbVos = staffDao.selectstId(testpaperid);//[1,2]试题id
        for(int i=0;i<sjstzjbVos.size();i++){
//           int a=sjstzjbVos.get(i).getTestid();
            System.out.println("id:====================="+sjstzjbVos.get(i).getTestid());
            System.out.println("aaaaaaaaaa"+staffDao.selecttmxx(sjstzjbVos.get(i).getTestid()).get(0).getOptioncontent());
            List<String> list=new ArrayList<String>();;
            for (int j=0;j<staffDao.selecttmxx(sjstzjbVos.get(i).getTestid()).size();j++){
                System.out.println(staffDao.selecttmxx(sjstzjbVos.get(i).getTestid()).get(j).getOptioncontent());
                list.add(staffDao.selecttmxx(sjstzjbVos.get(i).getTestid()).get(j).getOptioncontent());
                System.out.println("111111");
            }
            sjstzjbVos.get(i).setAnswers(list);
            System.out.print(sjstzjbVos.toString());

        }
        return sjstzjbVos;
    }

    @Override
    public String selectpd(String optioncontent) {
        return staffDao.selectpd(optioncontent);
    }

    @Override
    public int updatezt(int examinationid) {
        return staffDao.updatezt(examinationid);
    }

    @Override
    public int insertjl(Record record) {
        return staffDao.insertjl(record);
    }

    @Override
    public String selectmc(int examinationid) {
        return staffDao.selectmc(examinationid);
    }

    @Override
    public List<Record> selectrecord(int userid) {
        return staffDao.selectrecord(userid);
    }

    @Override
    public List<Questionbank> selectfl() {
        return staffDao.selectfl();
    }

    @Override
    public PageInfo<Questionbank> selecttk(int pageNum, int pageSize,String qbname, String qbclassification) {
        Page<Questionbank> page= PageHelper.startPage(pageNum,pageSize);
        List<Questionbank> list=staffDao.selecttk(qbname,qbclassification);
        Page<Questionbank>address=new Page<>();
        BeanTools.copyList(list,address,Questionbank.class);
        PageInfo<Questionbank>pageInfo=new PageInfo<>(address);
        return pageInfo;
    }

    @Override
    public List<TquestionsEntity> selectcount(int questionbankid) {
        return staffDao.selectcount(questionbankid);
    }

    @Override
    public List<TquestionsEntity> selectstid(int questionbankid,String tqtype) {
        List<TquestionsEntity> selectstid = staffDao.selectstid(questionbankid,tqtype);
        for(int i=0;i<selectstid.size();i++){
            List<String> list=new ArrayList<String>();
            List<String> list1=new ArrayList<String>();
            for(int j=0;j<staffDao.selecttmxx(selectstid.get(i).getTestid()).size();j++){
                list.add(staffDao.selecttmxx(selectstid.get(i).getTestid()).get(j).getOptioncontent());
                list1.add(staffDao.selecttmxx(selectstid.get(i).getTestid()).get(j).getIsanswers());
            }
            selectstid.get(i).setAnswers(list);
            selectstid.get(i).setIsanswers(list1);
        }
        System.out.println(selectstid.toString());
        return selectstid;
    }

    @Override
    public Questionbank selecttjst(int questionbankid) {
        return staffDao.selecttjst(questionbankid);
    }

    @Override
    public int updatetkzt(int questionbankid) {
        return staffDao.updatetkzt(questionbankid);
    }


}
