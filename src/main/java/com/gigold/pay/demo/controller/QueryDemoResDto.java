/**
 * Title: QueryDemoResponseDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.controller;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: QueryDemoResponseDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6:16:49
 *
 */
public class QueryDemoResDto extends ResponseDto{
    private Person person;

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
