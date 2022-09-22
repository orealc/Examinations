package com.trkj.esexpaperservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.dao.QuestionbankDao;
import com.trkj.esexpaperservice.entity.Questionbank;
import com.trkj.esexpaperservice.entity.Tquestions;
import com.trkj.esexpaperservice.util.BeanTools;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@DubboService
public class QuestionbankServiceImpl implements QuestionbankService {
    @Autowired
    private QuestionbankDao questionbankDao;
    //查询题库所有
//    @Override
//    @Transactional
//    public List<Questionbank> selAll() {
//        return questionbankDao.selAll();
//    }

    //新增题库
    @Override
    @Transactional
    public int tkxz(Questionbank questionbank) {
        int insertt=questionbankDao.tkxz(questionbank);
        return insertt;
    }

    @Override
    @Transactional
    public PageInfo<Questionbank> selname(String qbname, int pageNum, int pageSize) {
        Page<QuestionbankDao> page= PageHelper.startPage(pageNum,pageSize);
        List<Questionbank>list=questionbankDao.selname(qbname);
        Page<Questionbank> a=new Page<>();
        BeanTools.copyList(list,a,Questionbank.class);
        PageInfo<Questionbank>pageInfo=new PageInfo<>(a);
        return pageInfo;
    }

    @Override
    public Questionbank findtkid(int questionbankid) {
        return questionbankDao.findtkid(questionbankid);
    }

    @Override
    public Questionbank uptikus(Questionbank questionbank) {
        Questionbank j = new Questionbank();
        BeanTools.copyProperties(questionbank, j);
        questionbankDao.uptikus(j);
        return questionbank;
    }
}
