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

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.demo.dao.DemoDAO;

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

}
