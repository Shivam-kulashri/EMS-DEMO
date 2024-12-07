package com.springboot.EMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.EMS.dto.ApplicantsDetailDto;
import com.springboot.EMS.model.Application;
import com.springboot.EMS.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public List<Application> insertInBatch(List<Application> list) {
		return applicationRepository.saveAll(list);
	}

	public List<Application> getAllApplication() {
		return applicationRepository.findAll();
	}
	
	public List<ApplicantsDetailDto> getClearedApplications() {
        List<Object[]> rawData = applicationRepository.findClearedApplications();
        List<ApplicantsDetailDto> dtos = new ArrayList<>();

        for (Object[] record : rawData) {
            ApplicantsDetailDto dto = new ApplicantsDetailDto();
            dto.setJobSeekerName((String) record[0]);  		// name
            dto.setEmail((String) record[1]);         		// email
            dto.setWorkExperience((String) record[2]); 		// workExperience
            dto.setJobTitle((String) record[3]);      		// jobTitle
            dto.setApplicationStatus((String) record[4]); 	// applicationStatus

            dtos.add(dto);
        }

        return dtos;
    }
}
