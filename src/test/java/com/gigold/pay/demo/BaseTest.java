/**
 * gigold Inc.  吉高支付 湖南宝伦天地信息科技有限公司
 * Copyright (c) 2015 All Rights Reserved.
 */
package com.gigold.pay.demo;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Context;
import com.gigold.pay.framework.core.RequestInfo;
import com.gigold.pay.framework.util.DateUtil;
import java.util.Date;

/**
 * 单元测试基类
 *
 * @author mingbo.tang
 * ion $Id: BaseTest.java,v 0.1 2015年10月16日 16:55 mingbo.tang $Exp
 */
public class BaseTest {

    public static final String CTX_PROP_SPACE_APP = "@APP";

    public static final String REQ_INFO = "REQ_INFO";

    static {
        // 初始化当前上下文
        Context ctx = Context.createContext("junit test context", null);
        Context.pushCurrentContext(ctx);

        RequestInfo info = new RequestInfo();
        info.setNodId("junit nod");
        info.setInsId("junit inst");
        info.setModId("junit mod");
        info.setSysCnl("junit syscnl");
        info.setMsgId("junit" + DateUtil.getLongDateString(new Date()));
        info.setRequestTime(System.currentTimeMillis());
        info.setResponseTime(System.currentTimeMillis());
        info.setRemoteIp("127.0.0.1");
        info.setCode("success");
        info.setDesc("success");
        info.setUserAgent("junit agent");
        info.setMsgCd("00000");
        info.setMsgInf("success");
        info.setUserNo("junit user 1");
        info.setUsrToken("junit user token");
        RequestInfo.setCurInstance(info);

        SystemPropertyConfigure cnf = new SystemPropertyConfigure();

        cnf.setProperty("sys.home", "/app/ver/");
        cnf.setProperty("sys.runmode", "develop");
        cnf.setProperty("sys.encoding", "UTF-8");
        cnf.setProperty("instance.id", "SYS");
        cnf.setProperty("log.level", "DEBUG");
        cnf.setProperty("log.limits_lines", "20");
    }
}
