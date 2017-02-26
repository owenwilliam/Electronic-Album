package com.owen;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitTest
{
	@Test
    public void testSpring()
    {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    }
}
