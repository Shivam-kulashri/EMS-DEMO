package com.springboot.EMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.EMS.model.InterviewSchedular;
import com.springboot.EMS.model.Interviewer;

public interface InterviewSchedularRepository extends JpaRepository<InterviewSchedular, Integer> {

	@Query("SELECT DISTINCT i.interviewer FROM InterviewSchedular i WHERE i.interviewDate = :date")
	List<Interviewer> findInterviewersByScheduleDate(@Param("date") String date);
	
	@Query("SELECT i FROM InterviewSchedular i "
			+ "WHERE i.interviewer.id =?1")
	Optional<List<InterviewSchedular>> findInterviewAllById(int id);

}
