package com.gigold.pay.demo.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.gigold.pay.demo.DubboServiceFacade;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;

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
	public DubboServiceFacade dubboService;
	
	
	
	
	public String sayHello(String info) throws PendingException{
		String message=null;
		if (dubboService != null) {
			try {
				message=dubboService.hello();
			}  catch (RpcException e) {
				throw new PendingException(SysCode.RPC_FAIL, "服务不可用", e);
			} catch (Exception e) {
				throw new PendingException(SysCode.RPC_FAIL, "其他异常", e);
			}
		} else {
			throw new PendingException(SysCode.RPC_FAIL, "服务不可用");

		}
		return message;
	}
}
