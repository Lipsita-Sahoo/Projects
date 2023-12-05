package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BatchMaster;

public interface BatchRepository extends JpaRepository<BatchMaster, Integer> {

}
