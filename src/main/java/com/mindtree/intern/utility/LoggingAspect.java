package com.mindtree.intern.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Autowired
	private Environment enviroment;
	
	public final Log LOGGER=LogFactory.getLog(LoggingAspect.class);
	@AfterThrowing(pointcut= "execution(* com.mindtree.intern.service.*Impl.*(..))",throwing="exception")
	public void loggingAspect(Exception exception)
	{
		LOGGER.error(enviroment.getProperty(exception.getMessage(),exception.getMessage()));
	}
}
