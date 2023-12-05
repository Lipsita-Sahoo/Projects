package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BatchAllocate;
import com.example.demo.repository.BatchAllocateRepository;

@Service
public class BatchAllocateServiceImpl implements BatchAllocateService {

	@Autowired
	private BatchAllocateRepository batchAllocateRepo;
	
	
	@Override
	public List<BatchAllocate> getTechByBatch(Integer batchId) {
		
		return batchAllocateRepo.getTechByBatch(batchId);
	}


	@Override
	public List<BatchAllocate> getEmpById(Integer batchId, Integer technologyId) {
		
		return batchAllocateRepo.getEmpById(batchId,technologyId);
	}

}
