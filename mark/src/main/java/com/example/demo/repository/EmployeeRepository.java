package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EmployeeMaster;

public interface EmployeeRepository extends JpaRepository<EmployeeMaster, Integer> {

}
