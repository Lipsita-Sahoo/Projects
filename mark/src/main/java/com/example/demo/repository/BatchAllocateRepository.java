package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.BatchAllocate;

public interface BatchAllocateRepository extends JpaRepository<BatchAllocate, Integer> {

	@Query("From BatchAllocate where batchId.batchId = :batchId")
	List<BatchAllocate> getTechByBatch(Integer batchId);

	@Query("From BatchAllocate where batchId.batchId = :batchId AND technologyId.technologyId = :technologyId")
	List<BatchAllocate> getEmpById(Integer batchId, Integer technologyId);

}
