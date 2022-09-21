package com.trkj.esexpaperservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trkj.esexpaperservice.dao.QuestionbankDao;
import com.trkj.esexpaperservice.dao.TquestionsDao;
import com.trkj.esexpaperservice.entity.Questionbank;
import com.trkj.esexpaperservice.entity.Tquestions;
import com.trkj.esexpaperservice.util.BeanTools;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DubboService
public class TquestionsServiceImpl implements TquestionsService {
    @Autowired
    private TquestionsDao tquestionsDao;

    //条件查询
    @Override
    @Transactional
    public PageInfo<Tquestions> eqselname(String tcontent, int pageNum, int pageSize) {
        Page<TquestionsDao> page = PageHelper.startPage(pageNum, pageSize);
        List<Tquestions> list = tquestionsDao.eqselname(tcontent);
        Page<Tquestions> qbst = new Page<>();
        BeanTools.copyList(list, qbst, Tquestions.class);
        PageInfo<Tquestions> pageInfo = new PageInfo<>(qbst);
        return pageInfo;
    }

    @Override
    @Transactional
    public int insst(Tquestions tquestions) {
        int insst = tquestionsDao.insst(tquestions);
        return insst;
    }

    @Override
    @Transactional
    public Tquestions findid(int testid) {
        return tquestionsDao.findid(testid);
    }

    @Override
    @Transactional
    public List<Tquestions> selstname(int questionbankid) {
        return tquestionsDao.selstname(questionbankid);
    }

    @Override
    @Transactional
    public Tquestions uptquestion(Tquestions tquestions) {
        Tquestions j = new Tquestions();
        BeanTools.copyProperties(tquestions, j);
        tquestionsDao.uptquestion(j);
        return tquestions;
    }
}
