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
import com.springboot.EMS.model.Hr;
import com.springboot.EMS.service.HrService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class HrController {

	@Autowired
	private HrService hrService;

	@PostMapping("/hr/batch/addhr")
	public List<Hr> addHrBatch(@RequestBody List<Hr> list) {
		return hrService.insertInBatch(list);
	}
	
	@PostMapping("/hr/addHr")
	public Hr addHr(@RequestBody Hr hr) {
		return hrService.addHr(hr);
	}
	
	@PutMapping("/hr/update/{id}")
	public ResponseEntity<?> updateHr(@PathVariable int id,@RequestBody Hr newHr,ResponseMessageDto dto){
		try {
			Hr oldHr = hrService.validate(id);
		if(newHr.getName()!=null)
			oldHr.setName(newHr.getName());
		if(newHr.getContact()!=null)
			oldHr.setContact(newHr.getContact());
		if(newHr.getEmailId()!=null)
			oldHr.setEmailId(newHr.getEmailId());
		
		oldHr = hrService.addHr(oldHr);
		
		return ResponseEntity.ok(oldHr);
		} catch(ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/hr/all")
	public List<Hr> getAllHr() {
		List<Hr> hr = hrService.getAllHr();
		return hr;
	}
	
	@GetMapping("/hr/getApplicantDetails")
	public ResponseEntity<List<ApplicantsDetailDto>> getApplicantDetails(){
		List<ApplicantsDetailDto> jobApplications = hrService.getApplicantDetails();
		return ResponseEntity.ok(jobApplications);
	}
}
