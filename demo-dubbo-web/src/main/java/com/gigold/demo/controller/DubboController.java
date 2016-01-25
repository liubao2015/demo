/**
 * Title: DemoController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.demo.facade.XXXReqDto;
import com.gigold.pay.demo.integration.DubboConsumerIntegration;
import com.gigold.pay.demo.integration.PubMsgIntegration;
import com.gigold.pay.framework.core.ResponseDto;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;

/**
 * Title: DemoController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author Devin
 * @date 2015年9月16日下午6 :13:29
 */
@Controller
@RequestMapping("/")
public class DubboController extends BaseController {

	@Autowired
	public DubboConsumerIntegration dubboConsumerIntegration;
	@Autowired
	public PubMsgIntegration pubMsgIntegration;

	/**
	 * 
	 * Title: query<br/>
	 * Description: 通过service包装一层来调用dubbo<br/>
	 * 
	 * @author xiebin
	 * @date 2016年1月15日上午11:42:08
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query.do")
	public @ResponseBody ResponseDto query(@RequestBody XXXReqDto reqDto) {
		ResponseDto res = new ResponseDto();
		if (reqDto == null) {
			debug("reqDto 为空");
			res.setRspCd(CodeItem.DEMO_FAIL);
		}

		if (!reqDto.validate()) {
			debug("验证不通过");
			res.setRspCd(CodeItem.VAILDATE_FAIL);
		}
		
		debug("调用query：");
		String message;
		try {
			message = dubboConsumerIntegration.sayHello(reqDto);
			debug("调用Dubbo返回的信息－－－－－>" + message);
			res.setRspCd(SysCode.SUCCESS);
		} catch (PendingException e) {
			res.setRspCd(e.getCode());
			e.printStackTrace();
		}
		return res;
	}
	
	
	@RequestMapping(value = "/get.do")
	public @ResponseBody ResponseDto getInfo() {
		ResponseDto res = new ResponseDto();
		debug("调用getInfo：");
		int count;
		try {
			count = pubMsgIntegration.updateMsgInfo("DEMO");
			debug("调用Dubbo返回的信息－－－－－>" + count);
			res.setRspCd(SysCode.SUCCESS);
		} catch (PendingException e) {
			res.setRspCd(e.getCode());
			e.printStackTrace();
		}
		return res;
	}
	

}
