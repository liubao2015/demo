/**
 * Title: DubboService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.service;

import com.alibaba.dubbo.rpc.RpcException;
import com.gigold.pay.demo.DubboServiceFacade;

/**
 * Title: DubboService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2016年1月14日下午9:15:06
 *
 */
public class DubboServiceImpl implements DubboServiceFacade {

	/* （non Javadoc）
	 * Title: hello<br/>
	 * <p>Description: <br/>
	 * @return
	 * @see com.gigold.pay.demo.rpc.IDubboService#hello()
	 */
	@Override
	public String hello() throws RpcException{
		return "hello dubbo";
	}

}
