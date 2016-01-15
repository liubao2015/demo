package com.gigold.pay.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gigold.pay.demo.rpc.IDemoDubboService;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.exception.AbortException;

/**
 * Hello world!
 *
 */
@Service
public class DubboConsumerService  extends Domain
{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Reference
	private IDemoDubboService demoDubboService;
	
	
	public String sayHello(String info){
		String message=null;
		try {
			message=demoDubboService.sayHello(info);
		} catch (AbortException e) {
			debug(e.getMessage());
			e.printStackTrace();
		}
		return message;
	}
}
