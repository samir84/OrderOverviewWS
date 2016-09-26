package com.hs.eai.orderoverview.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentationController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String documentation(){
		return "Welcome to Project management api..";
	}
	
}
