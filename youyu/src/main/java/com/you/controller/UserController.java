package com.you.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.you.bean.Money;
import com.you.bean.User;
import com.you.service.MoneyService;
import com.you.service.UserService;

@Controller  

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class UserController {
	   //在后台输出   
	  private static Logger logger = Logger.getLogger(UserController.class);  
	    @Resource  
	    private UserService userService ;  
	    @Resource  
	    private MoneyService moneyService ;  
	    
	    public void test1() {  
	        User user = userService.getUserById(1);  
	        System.out.println(JSON.toJSONString(user));
	        logger.info(JSON.toJSONString(user));  
	    } 
	    
	    public void test2() {  
	    	User user = new User();
	    	user.setAge(12);
	    	user.setId(2);
	    	user.setName("yang");
	    	user.setPassword("123456");
	    	//插入成功返回1
	    	int hj = userService.insert(user);  
	        System.out.println(hj);
	        logger.info(JSON.toJSONString(user));  
	    }  
	   
	    public void test3(){
	    	  List list= userService.selectAll();
	    	  System.out.println(JSON.toJSONString(list));
	    }
	    public void test4(){
	    	  User user = new User();
	    	  user.setAge(22);
	    	  user.setName("33");
	    	  user.setPassword("123456");
	    	  int list= userService.update(1);
	    	  System.out.println(JSON.toJSONString(list));
	    }
	 
	  public void test5()
	  {
		  User user = new User();
		  user.setId(2);
    	  user.setAge(22);
    	  user.setName("33");
    	  user.setPassword("123456");
    	  int list= userService.update2(user);
    	  System.out.println(JSON.toJSONString(list));
	  }
	  public void test6(){
		  User user = new User();
		  user.setId(1);
    	  int list= userService.delete(user);
    	  System.out.println(JSON.toJSONString(list));
	  }
	  //简单事物的操作，向一个表中插入多条数据，有一条不成功就回滚
	/*  public void test7(){
		  List<User> l = new ArrayList<User>();
			  for(int i = 0;i<6;i++){
				  User user = new User();
				  user.setId(i);
				  user.setAge(i);
				  user.setName("xx"+i);
				  user.setPassword("xxword"+i);
				  l.add(user);
			  }
		  userService.traninsert(l); 
	  }*/
	  
	  public void insertmoney(){
		  	Money money = new Money();
		  	money.setId(55);
		  	money.setMoney("5500$");
		  	moneyService.insertMoney(money);
		  	
	  }
	  //简单事物，像两个表中分别插入数据，不成功就回滚
	  @Test
	  public void test8(){
		  List<User> l = new ArrayList<User>();
		  for(int i = 0;i<6;i++){
			  User user = new User();
			  user.setId(i);
			  user.setAge(i);
			  user.setName("xx"+i);
			  user.setPassword("xxword"+i);
			  l.add(user);
		  }
			Money money = new Money();
		  	money.setId(55);
		  	money.setMoney("5500$");
		    userService.traninsert(l,money); 
	  }
}
