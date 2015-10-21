/**
 * Title: DemoDubboService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.service;
        
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.dao.DemoDAO;
import com.gigold.pay.demo.rpc.IDemoDubboService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: DemoDubboService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月28日下午1:13:54
 *
 */

@Service
public class DemoDubboService extends Domain implements  IDemoDubboService{
    
    
    
    @Autowired
    private DemoDAO dao;
    
    
    @Transactional(rollbackFor = {AbortException.class })
    public String sayHello(String name) {
       
        Person u1 =  (Person) SpringContextHolder.getBean(Person.class);
        u1.setName(name);
        u1.setAge(20);
        
        dao.addPerson(u1);
        return "SUCCESS";
    }

    
    
    
    @Transactional(rollbackFor = {AbortException.class })
    public List getUsers() throws AbortException {
        List list = new ArrayList();
        Person u1 =  (Person) SpringContextHolder.getBean(Person.class);
        u1.setName("应该失败");
        u1.setAge(20);
        dao.addPerson(u1);
        throw new AbortException(CodeItem._FAIL,"");
       
    }
    
    public String sayHello2(String name) throws PendingException{
       throw new PendingException(CodeItem._FAIL,"ssss");
    }
}
