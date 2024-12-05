package com.springboot.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.EMS.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer>{

}
