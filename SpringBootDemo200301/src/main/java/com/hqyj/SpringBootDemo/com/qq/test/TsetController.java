package com.hqyj.SpringBootDemo.com.qq.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TsetController {

	@RequestMapping("/test/desc")
	@ResponseBody
	public String run(){
		return "this is a page.";
	}
	
	
	
	
}




























