package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AssesmentMark;
import com.example.demo.repository.AssesmentMarkRepository;

@Service
public class AssesmentMarkServiceImpl implements AssesmentMarkService {

	@Autowired
	public AssesmentMarkRepository asmRepo;
	
	@Override
	public AssesmentMark saveMark(AssesmentMark asmark) {
		
		return asmRepo.save(asmark);
	}

}
