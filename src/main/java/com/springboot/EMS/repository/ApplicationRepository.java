package com.springboot.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.EMS.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
