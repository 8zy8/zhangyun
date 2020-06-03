package com.hqyj.SpringBootDemo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hqyj.SpringBootDemo.modules.test.vo.ApplicationTest;

@Controller
public class TsetController {
	
	@Value("${server.port}")
	private int port;
	
	@Value("${com.thornbird.name}")
	private String name;
	
	@Value("${com.thornbird.age}")
	private int age;
	
	@Value("${com.thornbird.desc}")
	private String desc;
	
	@Value("${com.thornbird.random}")
	private String random;
	
	@Autowired
	private ApplicationTest applicationTest;

	private final static Logger LOGGER = LoggerFactory.getLogger(TsetController.class);
	
	/**
	 * 127.0.0.1/test/log
	 */
	@RequestMapping("/test/log")
	@ResponseBody
	public String logTest() {
		// TRACE<DEBUG<INFO<WARN<ERROR
		
		LOGGER.trace("This is trace log");
		LOGGER.debug("This is DEBUG log");
		LOGGER.info("This is INFO log");
		LOGGER.warn("This is WARN log");
		LOGGER.error("This is ERROR log");
		
		return "This is log test.";
	}
	
	@RequestMapping("/test/config")
	@ResponseBody
	public String config() {
		StringBuffer sb =new StringBuffer();
		sb.append(port).append("-------")
			.append(name).append("-------")
			.append(age).append("-------")
			.append(desc).append("-------")
			.append(random).append("<br>");
		
		sb.append(applicationTest.getName1()).append("----")
			.append(applicationTest.getAge1()).append("----")
			.append(applicationTest.getDesc1()).append("----")
			.append(applicationTest.getRandom1()).append("<br>");
		
		
		return sb.toString();
	}
	
	@RequestMapping("/test/desc")
	@ResponseBody
	public String run(){
		return "this is a page.";
	}
}




























