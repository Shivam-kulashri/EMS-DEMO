package com.springboot.EMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.EMS.dto.ApplicantsDetailDto;
import com.springboot.EMS.exception.ResourceNotFoundException;
import com.springboot.EMS.model.Hr;
import com.springboot.EMS.repository.HrRepository;

@Service
public class HrService {
	@Autowired
	private HrRepository hrRepository;

	public List<Hr> insertInBatch(List<Hr> list) {
		return hrRepository.saveAll(list);
	}

	public List<Hr> getAllHr() {
		return hrRepository.findAll();
	}

	public Hr addHr(Hr hr) {
		return hrRepository.save(hr);
	}

	public Hr validate(int id) throws ResourceNotFoundException{
		Optional<Hr> optional = hrRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Hr ID not found!");
		Hr hr=optional.get();		
		return hr;
	}

	public List<ApplicantsDetailDto> getApplicantDetails() {
        List<Object[]> rawResults = hrRepository.getApplicantDetails();
        List<ApplicantsDetailDto> applicantsDetails = new ArrayList<>();

        // Map raw query results to DTO
        for (Object[] row : rawResults) {
            ApplicantsDetailDto dto = new ApplicantsDetailDto();
            dto.setJobSeekerName((String) row[0]);
            dto.setEmail((String) row[1]);
            dto.setWorkExperience((String) row[2]);
            dto.setJobTitle((String) row[3]);
            dto.setApplicationStatus((String) row[4]);
            applicantsDetails.add(dto);
        }

        return applicantsDetails;
	}

}
