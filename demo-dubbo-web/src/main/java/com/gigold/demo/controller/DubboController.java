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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.gigold.pay.demo.DubboServiceFacade;
import com.gigold.pay.demo.service.DubboConsumerService;
import com.gigold.pay.framework.core.ResponseDto;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;



/**
 * Title: DemoController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6 :13:29
 */
@Controller
@RequestMapping("/")
public class DubboController extends BaseController {

    @Autowired 
  public DubboConsumerService dubboConsumerService;
    @Reference
	public DubboServiceFacade dubboService;
    /**
     * 
     * Title: query<br/>
     * Description: 通过service包装一层来调用dubbo<br/>
     * @author xiebin
     * @date 2016年1月15日上午11:42:08
     *  
     * @return
     */
    @RequestMapping(value = "/query.do")
    public @ResponseBody ResponseDto query()  {
    	ResponseDto res = new ResponseDto();
        debug("调用query：");
        String message;
		try {
			message = dubboConsumerService.sayHello("dubbo");
			res.setRspCd(SysCode.SUCCESS);
        	res.setRspInf(message);
		} catch (PendingException e) {
			res.setRspCd(e.getCode());
			e.printStackTrace();
		}
        return res;
    }
    /**
     * 
     * Title: getInfo<br/>
     * Description: 直接在controoler中引用dubbo服务<br/>
     * @author xiebin
     * @date 2016年1月15日上午11:42:47
     *
     * @return
     */
    @RequestMapping(value = "/get.do")
    public @ResponseBody ResponseDto getInfo()  {
    	ResponseDto res = new ResponseDto();
        debug("调用query：");
        String message;
		try {
			message = dubboService.hello();
			res.setRspCd(SysCode.SUCCESS);
        	res.setRspInf(message);
		} catch (RpcException e) {
			res.setRspCd(SysCode.RPC_FAIL);
			e.printStackTrace();
		}
        return res;
    }
    
}

