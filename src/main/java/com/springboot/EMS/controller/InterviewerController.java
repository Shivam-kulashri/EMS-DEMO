package com.springboot.EMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.EMS.dto.ResponseMessageDto;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.InterviewSchedular;
import com.springboot.EMS.model.Interviewer;
import com.springboot.EMS.service.InterviewSchedularService;
import com.springboot.EMS.service.InterviewerService;

@RestController
public class InterviewerController {
	@Autowired
	private InterviewerService interviewerService;
	@Autowired
	private InterviewSchedularService interviewSchedularService;

	@PostMapping("/batch/addInterviewer")
	public List<Interviewer> addInterviewerBatch(@RequestBody List<Interviewer> list) {
		return interviewerService.insertInBatch(list);
	}

	@PostMapping("/scheduleinterview")
	public ResponseEntity<?> scheduleInterview(@RequestBody List<InterviewSchedular> list, ResponseMessageDto dto) {
		try {
			for (InterviewSchedular schedular : list) {
				Interviewer interviewer = schedular.getInterviewer();
				// ID validation
				if (interviewer == null || !interviewerService.validate(interviewer.getId())) {
					return ResponseEntity.badRequest().body(dto);
				}
			}

			interviewSchedularService.insertInBatch(list);
			return ResponseEntity.ok("Interview Scheduled successfully");
		} catch (

		Exception e) {
			return ResponseEntity.badRequest().body(dto);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateInterview(@PathVariable int id, @RequestBody InterviewSchedular newInterview,
			ResponseMessageDto dto) {
		try {
			InterviewSchedular existindInterview = interviewSchedularService.validate(id);
			if (newInterview.getInterviewDate() != null)
				existindInterview.setInterviewDate(newInterview.getInterviewDate());
			if (newInterview.getInterviewTime() != null)
				existindInterview.setInterviewTime(newInterview.getInterviewTime());
			if (newInterview.getInterviewMode() != null)
				existindInterview.setInterviewMode(newInterview.getInterviewMode());
			if (newInterview.getInterviewStatus() != null)
				existindInterview.setInterviewStatus(newInterview.getInterviewStatus());
			if (newInterview.getInterviewType() != null)
				existindInterview.setInterviewType(newInterview.getInterviewType());
			if (newInterview.getLastUpdated() != null)
				existindInterview.setLastUpdated(newInterview.getLastUpdated());
			if (newInterview.getInterviewer() != null)
				existindInterview.setInterviewer(newInterview.getInterviewer());

			existindInterview = interviewSchedularService.insert(existindInterview);

			return ResponseEntity.ok(existindInterview);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());

			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/interviewers/scheduled")
	public List<Interviewer> getInterviewersByScheduleDate(@RequestParam String date) {
		return interviewSchedularService.getInterviewersByScheduleDate(date);
	}

	@GetMapping("/interviewers/all")
	public List<Interviewer> getAllInterviewer() {
		List<Interviewer> interviewer = interviewerService.getAllInterviewer();
		return interviewer;
	}

}
