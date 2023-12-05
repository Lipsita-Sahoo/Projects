package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class BatchAllocate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer batchAllocateId;
	
	@ManyToOne
	@JoinColumn(name="batchId")
	private BatchMaster batchId;
	
	@ManyToOne
	@JoinColumn(name="technologyId")
	private TechnologyMaster technologyId;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	private EmployeeMaster employeeId;
	
}
