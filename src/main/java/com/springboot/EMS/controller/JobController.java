package com.springboot.EMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.EMS.dto.ResponseMessageDto;
import com.springboot.EMS.exception.IDnotfoundException;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.Job;
import com.springboot.EMS.service.JobService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class JobController {
	@Autowired
	private JobService jobService;

	@PostMapping("/batch/add")
	public List<Job> addJobBatch(@RequestBody List<Job> list) {
		return jobService.insertInBatch(list);
	}

	@GetMapping("/job/all")
	public List<Job> getAllJob() {
		List<Job> job = jobService.getAllJob();
		return job;
	}
	
	@PostMapping("/job/add")
	public Job addJob(@RequestBody Job job) {
		return jobService.addJob(job);
	}
	@DeleteMapping("/job/delete/{id}")
	public ResponseEntity<?> deleteJob(@PathVariable int id, ResponseMessageDto dto) {
		try {
			jobService.validate(id);
			jobService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		dto.setMsg("Job deleted");
		return ResponseEntity.ok(dto);
		
	}
	@GetMapping("/job/type/get")
	public ResponseEntity<?> getJobsByType(@RequestParam String job_type) {
	    try {
	        List<Job> list = jobService.getJobsByType(job_type);
	        return ResponseEntity.ok(list);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	// search by job id
			@GetMapping("/getJobsbyid/{jobid}")
			public ResponseEntity<Job> getJobsbyid(@PathVariable int jobid) throws IDnotfoundException {
				Job job = jobService.getJobsByid(jobid);
				
				return new ResponseEntity<>(job, HttpStatus.OK);
			}
			@PutMapping("/job/update/{id}")
		    public ResponseEntity<?> updateJob(@PathVariable int id, @RequestBody Job newJob, ResponseMessageDto dto) {
		        try {
		            Job existingJobDb = jobService.validate(id);
		            
		            if (newJob.getTitle() != null)
		                existingJobDb.setTitle(newJob.getTitle());
		            if (newJob.getDescription() != null)
		                existingJobDb.setDescription(newJob.getDescription());
		            if (newJob.getLocation() != null)
		                existingJobDb.setLocation(newJob.getLocation());
		            if (newJob.getQualifications() != null)
		                existingJobDb.setQualifications(newJob.getQualifications());
		            if (newJob.getExperienceRequired() != null)
		                existingJobDb.setExperienceRequired(newJob.getExperienceRequired());
		            
		            if (newJob.getJobType() != null)
		                existingJobDb.setJobType(newJob.getJobType());
		            if (newJob.getSalaryRange() != null)
		                existingJobDb.setSalaryRange(newJob.getSalaryRange());
		            if (newJob.getApplicationDeadline() != null)
		                existingJobDb.setApplicationDeadline(newJob.getApplicationDeadline());
		            if (newJob.getDatePosted() != null)
		                existingJobDb.setDatePosted(newJob.getDatePosted());

		            // Re-save the existing job with new values
		            existingJobDb = jobService.insert(existingJobDb);
		            return ResponseEntity.ok(existingJobDb);
		        } catch (ResourceNotFoundException e) {
		            dto.setMsg(e.getMessage());
		            return ResponseEntity.badRequest().body(dto);
		        }
		    }

}
