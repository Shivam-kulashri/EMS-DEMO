package com.springboot.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.EMS.model.JobSeeker;
import com.springboot.EMS.repository.JobSeekerRepository;

@Service
public class JobSeekerService {

	@Autowired
	JobSeekerRepository jobSeekerRepository;

	public List<JobSeeker> insertInBatch(List<JobSeeker> list) {
		return jobSeekerRepository.saveAll(list);
	}

	public List<JobSeeker> getAllJobSeeker() {
		return jobSeekerRepository.findAll();
	}

}
