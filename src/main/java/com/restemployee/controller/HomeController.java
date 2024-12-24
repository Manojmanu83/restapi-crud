package com.restemployee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String loadHome() {
		return "redirect:http://localhost:8080/swagger-ui/index.html";
	}
//	dockerdesktop
}
