/**
 * Title: DemoServiceImpl.java<br/>
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
import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: DemoServiceImpl<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午2:37:27
 *
 */
@Service
public class DemoService {
    
    
    @Autowired
    private DemoDAO dao;
    /* （non Javadoc）
     * Title: query<br/>
     * <p>Description: <br/>
     * @param name
     * @return
     * @throws AbortException
     * @see com.gigold.pay.demo.service.DemoService#query(java.lang.String)
     */
  
    public Person query(String name) {
        return dao.search(name);
    }
    
    
    @Transactional(rollbackFor = {AbortException.class })
    public void addPerson(Person p) throws AbortException{
        dao.addPerson(p);
        throw new AbortException(CodeItem._FAIL,"failed");
       
    }
    
    
   
    public void addPerson1(Person p) throws AbortException{
        dao.addPerson(p);
        throw new AbortException(CodeItem._FAIL,"failed");
       
    }
    
    public void addPerson2(Person p) throws PendingException{
        dao.addPerson(p);
        throw new PendingException(CodeItem._FAIL,"failed");
       
    }

}
