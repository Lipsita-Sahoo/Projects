package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BatchAllocate;

public interface BatchAllocateService {

	List<BatchAllocate> getTechByBatch(Integer batchId);

	List<BatchAllocate> getEmpById(Integer batchId, Integer technologyId);

}
