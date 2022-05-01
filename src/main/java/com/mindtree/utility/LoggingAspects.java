package com.mindtree.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspects {
	@Autowired
	Environment ev;
	static final Log LOGGER=LogFactory.getLog(LoggingAspects.class);
	@AfterThrowing(pointcut = "execution(* com.mindtree.service.*Impl.*(..))",throwing = "exception")
	public void serviceLogging(Exception exception)
	{
		LOGGER.error(ev.getProperty(exception.getMessage(),exception.getMessage()));
	}
	@AfterThrowing(pointcut = "execution(* com.mindtree.repository.*.*(..))",throwing = "exception")
	public void repoLogging(Exception exception)
	{
		LOGGER.error(ev.getProperty(exception.getMessage(),exception.getMessage()));
	}

}
