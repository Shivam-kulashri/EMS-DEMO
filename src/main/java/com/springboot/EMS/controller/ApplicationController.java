package com.springboot.EMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.EMS.dto.ApplicantsDetailDto;
import com.springboot.EMS.dto.ResponseMessageDto;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.Application;
import com.springboot.EMS.service.ApplicationService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/application/batch/add")
	List<Application> insertInBatch(@RequestBody List<Application> list) {
		return applicationService.insertInBatch(list);
	}
	
	@PostMapping("/application/add")
	public Application insertApplication(@RequestBody Application application) {
		return applicationService.insertApplication(application);
	}

	@GetMapping("/application/all")
	public List<Application> getAllInterview() {
		List<Application> application = applicationService.getAllApplication();
		return application;
	}
	
	@GetMapping("/application/getAllCleared")
	public List<ApplicantsDetailDto> getAllClearedApplications() {
        return applicationService.getClearedApplications();
    }
	
	@GetMapping("/application/getAllOnboarded")
	public List<ApplicantsDetailDto> getAllOnboardedApplications() {
        return applicationService.getOnboardedApplications();
    }
	
	@PutMapping("/application/update/{id}")
	public ResponseEntity<?> updateApplication(@PathVariable int id, @RequestBody Application app, ResponseMessageDto dto){
		try {
			Application oldApp = applicationService.validate(id);
		
			if(app.getApplicationStatus()!=null)
				oldApp.setApplicationStatus(app.getApplicationStatus());
			
			oldApp=applicationService.insertApplication(oldApp);
			
			return ResponseEntity.ok(oldApp);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			
			return ResponseEntity.badRequest().body(dto);
		}
	}
}
