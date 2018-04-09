package com.mall.butler.port.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase{
	protected ApplicationContext appContext;
	public TestBase(){
		appContext = new ClassPathXmlApplicationContext("classpath:com/mall/butler/beans.xml");
	}
	
	public Object getBean(String name){
		return appContext.getBean(name);
	}

}
