/**
 * gigold Inc.  吉高支付 湖南宝伦天地信息科技有限公司
 * Copyright (c) 2015 All Rights Reserved.
 */
package com.gigold.pay.demo;

import com.gigold.pay.framework.base.transaction.GigoldTransactionTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author mingbo.tang
 * @version $Id: TransactionTemplateStuk.java,v 0.1 2015年10月19日 14:55 mingbo.tang $Exp
 */
public class TransactionTemplateStuk extends GigoldTransactionTemplate {

    @Override
    public <T> T execute(TransactionCallback<T> action) throws TransactionException {
        TransactionStatus status = new SimpleTransactionStatus();
        return action.doInTransaction(status);
    }
}
