package com.springboot.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.EMS.model.Interviewer;

public interface InterviewerRepository extends JpaRepository<Interviewer, Integer> {

}
