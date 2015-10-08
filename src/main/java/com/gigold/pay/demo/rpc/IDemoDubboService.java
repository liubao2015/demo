/**
 * Title: IDemoDubboService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.rpc;

import java.util.List;

import com.gigold.pay.framework.core.exception.AbortException;
import com.gigold.pay.framework.core.exception.PendingException;

/**
 * Title: IDemoDubboService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月28日下午1:17:39
 *
 */
public interface IDemoDubboService {
    
    public String sayHello(String name) ;
    public List getUsers() throws AbortException;
    public String sayHello2(String name) throws PendingException;
}
