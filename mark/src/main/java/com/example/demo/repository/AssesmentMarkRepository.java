package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AssesmentMark;

public interface AssesmentMarkRepository extends JpaRepository<AssesmentMark, Integer> {

}
