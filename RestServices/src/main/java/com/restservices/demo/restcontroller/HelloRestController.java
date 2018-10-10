package com.restservices.demo.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.demo.bean.HelloWorldBean;

@RestController
public class HelloRestController {

	@GetMapping("/helloWorld")
	public String getMessage()
	{
		return "HelloWorld!!";
	}
	
	
	@GetMapping("/helloWorld-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("HelloWorld");
	}
	
}
