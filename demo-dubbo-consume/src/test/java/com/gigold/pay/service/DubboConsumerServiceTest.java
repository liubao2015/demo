package com.gigold.pay.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gigold.pay.service.DubboConsumerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/**/spring/**/*Beans.xml"})
public class DubboConsumerServiceTest {
	@Autowired
	private DubboConsumerService dubboConsumerService;
	@Test
	public void testSayHello(){
		
		String message=dubboConsumerService.sayHello("dubbo");
		Assert.assertEquals("hello dubbo", message);
		
		
	}
}
