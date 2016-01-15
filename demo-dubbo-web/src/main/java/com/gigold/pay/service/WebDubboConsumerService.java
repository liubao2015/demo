package com.gigold.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.demo.service.DubboConsumerService;
import com.gigold.pay.framework.core.Domain;

/**
 * Hello world!
 *
 */
@Service
public class WebDubboConsumerService  extends Domain
{
    
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	public DubboConsumerService dubboConsumerService;
	
	public String sayHello(String info){
		String message=null;
		try {
			message=dubboConsumerService.sayHello("dubbo");
		} catch (Exception e) {
			debug(e.getMessage());
			e.printStackTrace();
		}
		return message;
	}
}
