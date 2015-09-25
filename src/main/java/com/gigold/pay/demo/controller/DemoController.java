/**
 * Title: DemoController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.service.DemoService;
import com.gigold.pay.framework.base.log.impl.BizLogger;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: DemoController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6:13:29
 *
 */
@Controller
@RequestMapping("/")
public class DemoController  {

    /** demoService，由Autowired自动注入 */
    @Autowired
    private DemoService demoService;

    /**
     * Title: query<br/>
     * Description: <br/>
     * 
     * @author ousei
     * @date 2014年12月15日下午9:05:03
     *
     * @param acNo
     *            账号
     * @return ${返回信息描述}
     * @throws Exception
     */
    @RequestMapping(value = "/query.do")
    public @ResponseBody QueryDemoResDto query(@RequestBody QueryDemoReqDto dto)  {
        
        BizLogger.info("调用query：");
        
        dto.validate();
        QueryDemoResDto res = new QueryDemoResDto();
        Person p=demoService.query(dto.getPerson().getName());
        BizLogger.info("传入的参数"+dto.getPerson().getName());
        if(p!= null){
            res.setPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        }else{
            res.setRspCd(CodeItem.DEMO_FAIL);
        }
        return res;
    }
    
    @RequestMapping(value = "/insert.do")
    public @ResponseBody QueryDemoResDto insert(HttpSession session)  {
        
        BizLogger.info("调用insert：");
        session.setAttribute("test", "陈志铉");
        session.setAttribute("test1", "czx");
        QueryDemoResDto res = new QueryDemoResDto();
        res.setRspCd(SysCode.SUCCESS);       
        return res;
    }
    
    
    @RequestMapping(value = "/get.do")
    public @ResponseBody QueryDemoResDto get(HttpSession session)  {
        BizLogger.info("调用get：");
        String test = (String)session.getAttribute("test");
        String test1 = (String)session.getAttribute("test1");
        BizLogger.info(test);
        BizLogger.info(test1);
        QueryDemoResDto res = new QueryDemoResDto();
        res.setRspCd(SysCode.SUCCESS);
        
        return res;
    }

    
    @RequestMapping(value = "/add.do")
    public @ResponseBody QueryDemoResDto add()  {
        BizLogger.info("调用add：");
        Person p = new Person();
        p.setName("事务失败");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
          
            res.setRspCd(SysCode.SYS_FAIL);
        }        
        return res;
    }
    
    
    @RequestMapping(value = "/add1.do")
    public @ResponseBody QueryDemoResDto add1()  {
        BizLogger.info("调用add1：");
        Person p = new Person();
        p.setName("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson1(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            // TODO Auto-generated catch block
          
            res.setRspCd(SysCode.SYS_FAIL);
        }        
        return res;
    }
    
    
    @RequestMapping(value = "/add2.do")
    public @ResponseBody QueryDemoResDto add2()  {
        BizLogger.info("调用add1：");
        Person p = new Person();
        p.setName("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson2(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (PendingException e) {
          
            res.setRspCd(SysCode.SYS_FAIL);
        }        
        return res;
    }
}

