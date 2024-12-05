package com.springboot.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
