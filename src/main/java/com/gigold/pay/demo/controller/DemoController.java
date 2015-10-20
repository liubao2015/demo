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
import com.gigold.pay.demo.service.Demo2Service;
import com.gigold.pay.demo.service.DemoService;
import com.gigold.pay.framework.base.DomainFactory;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.AbortExceptionLogger;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ReqHeader;
import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageInfo;


/**
 * Title: DemoController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6 :13:29
 */
@Controller
@RequestMapping("/")
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;
    
    /**
     * Title: query<br/>
     * Description: <br/>
     * 
     * @author ousei
     * @date 2014年12月15日下午9:05:03
     *
     * @return ${返回信息描述}
     * @throws Exception
     */
    @RequestMapping(value = "/query.do")
    public @ResponseBody QueryDemoResDto query(@RequestBody QueryDemoReqDto dto)  {
        
        debug("调用query：");
        dto.validate();
        QueryDemoResDto res = new QueryDemoResDto();
        Person p=demoService.query(dto.getPerson().getName());
        debug("传入的参数"+dto.getPerson().getName());
        if(p!= null){
            res.setPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        }else{
            res.setRspCd(CodeItem.DEMO_FAIL);
        }
        return res;
    }
    
    @RequestMapping(value = "/query2.do")
    public @ResponseBody QueryDemoResDto query2()  {
        
        debug("调用query：");
        QueryDemoResDto res = new QueryDemoResDto();
        
        
        ReqHeader header =getHeader();
        debug("请求的Session ID＝"+header.getTokenId());
        
        Person p=demoService.query("4");
        debug("传入的参数4");
        if(p!= null){
            res.setPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        }else{
            res.setRspCd(CodeItem.DEMO_FAIL);
        }
        return res;
    }
    
    
//    @RequestMapping(value = "/query2.do")
//    public @ResponseBody QueryDemoResDto query()  {
//        
//      
//        PageInfo<Person> pi=demoService.queryPage(1);
//        BizLogger.info("查到的参数"+pi.getSize());
//        
//        QueryDemoResDto res = new QueryDemoResDto();   
//        res.setPageInfo(pi);
//        res.setRspCd(SysCode.SUCCESS);
//      
//        return res;
//    }
    
    
    //事务成功，业务成功
    @RequestMapping(value = "/insert.do")
    public @ResponseBody QueryDemoResDto insert(HttpSession session)  {
        
        debug("调用insert：");
        session.setAttribute("test", "陈志铉");
        session.setAttribute("test1", "czx");
        Person p = DomainFactory.getInstance().getDomain(Person.class);
        p.setDesc("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            res.setRspCd(SysCode.SYS_FAIL);
            
            e.catchLog(this.getClass(), SysCode.SYS_FAIL);
        }    
       
        return res;
    }
    
    
    @RequestMapping(value = "/get.do")
    public @ResponseBody QueryDemoResDto get(HttpSession session)  {
        debug("调用get：");
        String test = (String)session.getAttribute("test");
        String test1 = (String)session.getAttribute("test1");
        debug(test);
        debug(test1);
        QueryDemoResDto res = new QueryDemoResDto();
        res.setRspCd(SysCode.SUCCESS);
        
        return res;
    }

  //演示业务显示失败，业务成为失败，因为调用的服务没有配置响应的事务
    @RequestMapping(value = "/add.do")
    public @ResponseBody QueryDemoResDto add()  {
        debug("调用add：");
        Person p = DomainFactory.getInstance().getDomain(Person.class);
        p.setDesc("事务失败");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPersonFail(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            res.setRspCd(SysCode.SYS_FAIL);
            e.catchLog(this.getClass(), SysCode.SYS_FAIL);
        }        
        return res;
    }
    
    
    
    //演示业务显示失败，但实际业务成功，因为调用的服务没有配置响应的事务
    @RequestMapping(value = "/add1.do")
    public @ResponseBody QueryDemoResDto add1()  {
        debug("调用add1：");
        Person p = DomainFactory.getInstance().getDomain(Person.class);
        debug("p in 容器"+p.getName());
        p.setDesc("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            // TODO Auto-generated catch block          
            res.setRspCd(SysCode.SYS_FAIL);
            e.catchLog(this.getClass(), SysCode.SYS_FAIL);
        }        
        return res;
    }
    
    
  //演示业务显示失败，但实际业务成功，因为调用的服务随配置了事务，但并没有触发，因为异常为其他异常
    @RequestMapping(value = "/add2.do")
    public @ResponseBody QueryDemoResDto add2()  {
        debug("调用add1：");
        Person p = (Person) SpringContextHolder.getBean(Person.class);
        p.setDesc("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            e.catchLog(this.getClass(), SysCode.SYS_FAIL);
            res.setRspCd(SysCode.SYS_FAIL);
        }        
        return res;
    }
    
    
  //演示业务触发数据库事务，违反唯一约束，导致自己回滚
    @RequestMapping(value = "/only.do")
    public @ResponseBody QueryDemoResDto only()  {
        debug("调用add1：");
        Person p = (Person) SpringContextHolder.getBean(Person.class);
        p.setName("我不能重复");
        p.setDesc("事务成功");
        QueryDemoResDto res = new QueryDemoResDto();
        try {
            demoService.addPerson(p);
            res.setRspCd(SysCode.SUCCESS);
        } catch (AbortException e) {
            e.catchLog(this.getClass(), SysCode.SYS_FAIL);
            res.setRspCd(SysCode.SYS_FAIL);
        }        
        return res;
    }


    /**
     * Sets demo service.
     *
     * @param demoService the demo service
     */
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}

