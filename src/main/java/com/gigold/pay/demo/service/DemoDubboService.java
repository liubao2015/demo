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

import org.springframework.stereotype.Service;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.rpc.IDemoDubboService;
import com.gigold.pay.framework.base.SpringContextHolder;
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
public class DemoDubboService implements  IDemoDubboService{

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public List getUsers() {
        List list = new ArrayList();
        Person u1 =  (Person) SpringContextHolder.getBean(Person.class);
        u1.setName("jack");
        u1.setAge(20);
        

        Person u2 =  (Person) SpringContextHolder.getBean(Person.class);
        u2.setName("tom");
        u2.setAge(21);
        

        list.add(u1);
        list.add(u2);
        
        return list;
    }
    
    public String sayHello2(String name) throws PendingException{
       throw new PendingException(CodeItem._FAIL,"ssss");
    }
}
