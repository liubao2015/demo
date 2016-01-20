package com.gigold.pay.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gigold.pay.demo.service.DubboConsumerService;
import com.gigold.pay.demo.service.XXXReqDto;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.service.AbstractService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/**/spring/**/*Beans.xml"})
public class DubboConsumerServiceTest extends AbstractService{
	@Autowired
	public  DubboConsumerService dubboConsumerService;
	@Test
	public void testSayHello(){
		XXXReqDto dto=new XXXReqDto();
		dto.setInfo("测试");
		String message=null;
		try {
			message = dubboConsumerService.sayHello(dto);
		} catch (PendingException e) {
			debug(e.getCode()+"---> 调用RPC服务异常");
		}
		Assert.assertEquals("hello dubbo--->测试", message);
		
		
	}
}
