/**
 * Title: DemoDubboService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.facade;
        
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.dao.DemoDAO;
import com.gigold.pay.demo.rpc.IDemoDubboService;
import com.gigold.pay.demo.service.DemoService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: DemoDubboService<br/>
 * Description: 对外封装的SOA服务，接口只传递基本类型：String，Integer,Long,Float等<br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月28日下午1:13:54
 *
 */

@Service
public class DemoDubboFacade  implements  IDemoDubboService{
    
    
    
    @Autowired
    private DemoService service;
    
    
   
    public String sayHello(String name) throws AbortException {
       
        Person u1 =  new Person();
        //u1.setName(name);
        u1.setDesc("ddd");
        u1.setAge(20);
        service.addPerson(u1);
        return "SUCCESS";
    }

    
    
    
  
    public List getUsers() throws AbortException {
        List list = new ArrayList();
        Person u1 =  new Person();
        u1.setName("应该失败");
        u1.setAge(20);
        service.addPerson(u1);
        throw new AbortException(CodeItem._FAIL,"");
       
    }
    
    public String sayHello2(String name) throws PendingException{
       throw new PendingException(CodeItem._FAIL,"ssss");
    }
}
