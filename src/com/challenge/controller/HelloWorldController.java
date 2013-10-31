package com.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	@RequestMapping("/challenge")
	public ModelAndView helloAppDirect() {
		return new ModelAndView("challenge", "app_direct", "Hello, AppDirect");
	}
}
