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

import com.gigold.pay.framework.core.ResponseDto;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.service.WebDubboConsumerService;



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
    public WebDubboConsumerService dubboConsumerService;
    
    /**
     * Title: query<br/>
     * Description: <br/>
     * 
     * @author ousei
     * @date 2014年12月15日下午9:05:03
     *
     * @return ${返回信息描述}
     * @throws Exception
     */
    @RequestMapping(value = "/query.do")
    public @ResponseBody ResponseDto query()  {
    	ResponseDto res = new ResponseDto();
        debug("调用query：");
        String message=dubboConsumerService.sayHello("dubbo");
        if(StringUtil.isNotBlank(message)){
        	res.setRspCd(SysCode.SUCCESS);
        	res.setRspInf(message);
        }else{
        	res.setRspCd(SysCode.RPC_FAIL);
        }
        return res;
    }
    
}

