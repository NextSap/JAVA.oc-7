package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private Logger logger = LogManager.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Model model) {
		logger.info("GET / called successfully");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("GET /admin/home called successfully");
		return "redirect:/bidList/list";
	}
}
