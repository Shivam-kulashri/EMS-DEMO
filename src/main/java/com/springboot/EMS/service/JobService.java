package com.springboot.EMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.EMS.exception.IDnotfoundException;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.Job;
import com.springboot.EMS.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public List<Job> insertInBatch(List<Job> list) {
		return jobRepository.saveAll(list);
	}

	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	public List<Job> getJobsByRole(String roleName) {
        return jobRepository.findJobsByRole(roleName);
    }

	public Job addJob(Job job) {
		return jobRepository.save(job);
	}
	public Job validate(int id) throws ResourceNotFoundException {
		Optional<Job> optional = jobRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Job id invalid");
		
		Job job = optional.get();
		return job; 
		
	}
	public void delete(int id) {
		jobRepository.deleteById(id);
		
	}
public Job insert(Job job) {
		
		return jobRepository.save(job);
	}
	public List<Job> getJobsByType(String job_type) {
		if (job_type == null || job_type.isEmpty()) {
            throw new IllegalArgumentException("Job type must be provided.");
        }

        // Assuming there's a method in the repository to find jobs by type
        return jobRepository.findByJobType(job_type);
	}
	public Job getJobsByid(int id) throws IDnotfoundException {
		return jobRepository.findById(id).orElseThrow(() -> new IDnotfoundException("Job not found with ID:"+id));

	}

}