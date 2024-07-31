package com.te.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
	
	StartController() {
		System.out.println("===> StartController 확인");
	}
	
	@GetMapping("/index")
	private String index() {
		System.out.println("===> index 확인");
		return "index.html";
	}
}
