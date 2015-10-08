/**
 * Title: Demo2Service.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.dao.DemoDAO;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: Demo2Service<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月28日上午11:55:23
 *
 */
@Service
public class Demo2Service extends Domain{
    
    
    @Autowired
    private DemoService service;
    
    @Autowired
    private DemoDAO dao;
    
    
    //理论上都失败
    @Transactional(rollbackFor = {AbortException.class })
    public void addPerson1(Person p) throws AbortException{
        dao.addPerson(p);
        debug("调用addPerson1");
        p.setName(p.getName()+"我是第二条");
        service.addPerson1(p);
    }
    
    //理论上应该也两条都回滚
    @Transactional(rollbackFor = {AbortException.class })
    public void addPerson(Person p) throws AbortException{
        dao.addPerson(p); 
        debug("调用addPerson");
        p.setName(p.getName()+"我是第二条");
        service.addPerson(p);
    }
    
    //理论上应该也两条都成功
    public void addPerson2(Person p) throws PendingException{
        dao.addPerson(p);
        p.setName(p.getName()+"我是第二条");
        service.addPerson2(p);
       
    }
    
    
    public Person query(String name) {
        return dao.search(name);
    }
}
