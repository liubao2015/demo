/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.controller;

import com.gigold.pay.demo.bo.Person;
import com.gigold.pay.framework.web.RequestDto;
import com.github.pagehelper.PageInfo;

/**
 * Title: QueryDemoRequestDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6:16:36
 *
 */
public class QueryDemoReqDto extends RequestDto{
    
    private Person person;
    private PageInfo pageInfo;
   
    
    
    
    
    

   
    
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










    public boolean validate(){
      return true;
    }
}
