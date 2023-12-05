package com.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csm.model.Citizen;
import com.csm.repository.CitizenRepo;

@Service

public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenRepo cityRepo;
	@Override
	public Citizen saveCitizen(Citizen c) {
		
		return cityRepo.save(c);
	}

}
