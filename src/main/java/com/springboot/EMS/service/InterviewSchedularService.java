package com.springboot.EMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.InterviewSchedular;
import com.springboot.EMS.model.Interviewer;
import com.springboot.EMS.repository.InterviewSchedularRepository;

@Service
public class InterviewSchedularService {
	@Autowired
	private InterviewSchedularRepository interviewSchedularRepository;

	public List<InterviewSchedular> insertInBatch(List<InterviewSchedular> list) {
		return interviewSchedularRepository.saveAll(list);
	}

	public InterviewSchedular validate(int id) throws ResourceNotFoundException {
		Optional<InterviewSchedular> optional = interviewSchedularRepository.findById(id);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Interview id invalid");

		InterviewSchedular interview = optional.get();
		return interview;
	}

	public InterviewSchedular insert(InterviewSchedular interviewSchedular) {
		return interviewSchedularRepository.save(interviewSchedular);
	}

	public List<Interviewer> getInterviewersByScheduleDate(String date) {
		return interviewSchedularRepository.findInterviewersByScheduleDate(date);
	}

	public List<InterviewSchedular> getAllInterview() {
		return interviewSchedularRepository.findAll();
	}
}
