package com.gigold.pay.demo.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gigold.pay.demo.rpc.IDubboService;
import com.gigold.pay.framework.core.Domain;

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
	public IDubboService dubboService;
	
	public String sayHello(String info){
		String message=null;
		try {
			message=dubboService.hello();
		} catch (Exception e) {
			debug(e.getMessage());
			e.printStackTrace();
		}
		return message;
	}
}
