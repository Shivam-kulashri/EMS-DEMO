package com.springboot.EMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.EMS.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
