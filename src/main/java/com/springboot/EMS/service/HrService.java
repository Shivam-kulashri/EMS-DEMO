package com.springboot.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
