package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeMaster;
import com.example.demo.repository.EmployeeRepository;

@Service

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public EmployeeMaster findByEmpId(Integer employeeId) {
		// TODO Auto-generated method stub
		return empRepo.findById(employeeId).get();
	}

}
