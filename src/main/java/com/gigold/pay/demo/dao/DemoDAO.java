/**
 * Title: DemoDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.demo.dao;

import java.util.List;

import com.gigold.pay.demo.bo.Person;

/**
 * Title: DemoDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午2:10:48
 *
 */

public interface DemoDAO {
  public Person search(String name);
  public Person searchForupdate(String name);
  public List<Person> searchALL();
  public void addPerson(Person p);
}
