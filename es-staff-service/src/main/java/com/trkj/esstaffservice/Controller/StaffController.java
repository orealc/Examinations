package com.trkj.esstaffservice.Controller;
import com.trkj.jwt.service.ExaminationService;
import com.trkj.jwt.service.StaffService;
import com.trkj.user.entity.Record;
import com.trkj.user.entity.TquestionsEntity;
import com.trkj.user.vo.SjstzjbVo;
import com.trkj.vo.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/StaffController")
@Slf4j
public class StaffController {
    @DubboReference
    private StaffService staffService;
    @GetMapping("/select")
    public AjaxResponse select(int pageNum, int pageSize,String examname){
        System.out.println("页数"+pageNum);
        System.out.println("大小"+pageSize);
        System.out.println("考试名称"+examname);
        return AjaxResponse.success(staffService.select(pageNum,pageSize,examname));
    }
    @GetMapping("/selectmn")
    public AjaxResponse selectmn(int pageNum, int pageSize,String examname){
        System.out.println("页数"+pageNum);
        System.out.println("大小"+pageSize);
        System.out.println("考试名称"+examname);
        return AjaxResponse.success(staffService.selectmn(pageNum,pageSize,examname));
    }
    @GetMapping("/selecttj")
    public AjaxResponse selecttj(int examinationid) {
        log.debug("数据{}",examinationid);
        //根据前端返回的考试id查询出试卷id
        int id=staffService.selectsjid(examinationid);
        //根据试卷id统计出试题数量
        SjstzjbVo vo=staffService.selectsum(id);
        return AjaxResponse.success(vo);
    }
    @GetMapping("/selecttm")
    public AjaxResponse selecttm(int examinationid){
        int id=staffService.selectsjid(examinationid); //根据前端返回的考试id查询出试卷id
       return AjaxResponse.success(staffService.selectstId(id));
    }
    @GetMapping("/selectjg")
    public AjaxResponse selectjg(String jg[]){
        System.out.println("数组数据"+jg.length);
        int row=0;
        for(int i=0;i<jg.length;i++){
            String optioncontent=jg[i];
            String a = staffService.selectpd(optioncontent);
            System.out.println(a);
            if(a.equals("是")){
                row++;
            }
        }
        System.out.println("答对"+row);
        return AjaxResponse.success(row);
    }
    @PutMapping("/update/{examinationid}")
    public AjaxResponse update(@PathVariable("examinationid") int examinationid) {
        int updatezt = staffService.updatezt(examinationid);
        System.out.println("修改成功");
        return AjaxResponse.success(updatezt);

    }
    @PostMapping("/insertjl/{examinationid}")
    public AjaxResponse insertjl(@PathVariable("examinationid") int examinationid,@RequestBody Record record){
        String selectmc = staffService.selectmc(examinationid);
        System.out.println("考试id"+examinationid);
        System.out.println("信息"+record.toString());
        Record entity=new Record();
        entity.setUserid(record.getUserid());
        entity.setHighestscore(record.getHighestscore());
        entity.setRecenttime(new Date());
        entity.setTestname(selectmc);
        System.out.println("时间"+new Date());
        return AjaxResponse.success(staffService.insertjl(entity));
    }
    @GetMapping("/selectjl")
    public AjaxResponse selectjl(int userid){
        return AjaxResponse.success(staffService.selectrecord(userid));
    }
    @GetMapping("/selectfl")
    public AjaxResponse selectfl(){
        return AjaxResponse.success(staffService.selectfl());
    }
    @GetMapping("/selecttk")
    public AjaxResponse selecttk(int pageNum, int pageSize,String qbname,String qbclassification){
        return AjaxResponse.success(staffService.selecttk(pageNum,pageSize,qbname,qbclassification));
    }
    @GetMapping("/selectcount")
    public AjaxResponse selectcount(int questionbankid){
        System.out.println("题库id"+questionbankid);
        List<TquestionsEntity> selectcount = staffService.selectcount(questionbankid);
        System.out.println("数据"+selectcount.toString());
        return AjaxResponse.success(selectcount);
    }
    @GetMapping("/selectstid")
    public AjaxResponse selectstid(int questionbankid,String tqtype){
        return AjaxResponse.success(staffService.selectstid(questionbankid,tqtype));
    }
    @GetMapping("/selecttjst")
    public AjaxResponse selecttjst(int questionbankid){
        return AjaxResponse.success(staffService.selecttjst(questionbankid));
    }
    @PutMapping("/updatetkzt/{questionbankid}")
    public AjaxResponse updatetkzt(@PathVariable("questionbankid") int questionbankid){
        return AjaxResponse.success(staffService.updatetkzt(questionbankid));
    }

}
