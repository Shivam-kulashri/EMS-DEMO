package com.springboot.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.EMS.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	 @Query("SELECT js.name, js.email, js.workExperience, j.title,a.id ,a.applicationStatus " +
	           "FROM Application a " +
	           "JOIN a.jobSeeker js " +
	           "JOIN a.job j " +
	           "WHERE a.applicationStatus = 'Cleared'")
	    List<Object[]> findClearedApplications();
	    
	  @Query("SELECT js.name, js.email, js.workExperience, j.title,a.id ,a.applicationStatus " +
		       "FROM Application a " 
		       + "JOIN a.jobSeeker js " 
		       + "JOIN a.job j " 
		       + "WHERE a.applicationStatus = 'Onboarded'")
		    List<Object[]> findOnboardedApplications();
}
