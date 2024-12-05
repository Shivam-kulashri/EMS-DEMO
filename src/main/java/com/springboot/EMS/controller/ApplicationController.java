package com.springboot.EMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.EMS.model.Application;
import com.springboot.EMS.service.ApplicationService;

@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/application/batch/add")
	List<Application> insertInBatch(@RequestBody List<Application> list) {
		return applicationService.insertInBatch(list);
	}

	@GetMapping("/application/all")
	public List<Application> getAllInterview() {
		List<Application> application = applicationService.getAllApplication();
		return application;
	}

}
