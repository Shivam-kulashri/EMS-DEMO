package com.springboot.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.EMS.model.Hr;

public interface HrRepository extends JpaRepository<Hr, Integer> {

	@Query("SELECT js.name, js.email, js.workExperience, j.title, a.applicationStatus " +
		       "FROM JobSeeker js " +
		       "JOIN Application a ON js.id = a.jobSeeker.id " +
		       "JOIN Job j ON a.job.id = j.id")
	List<Object[]> getApplicantDetails();

}
