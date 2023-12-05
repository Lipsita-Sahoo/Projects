package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class BatchMaster implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer batchId;
	
	//@NotBlank(message = "BatchName cannot be blank")
	private String batchName;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date batchStartDate;
	
	private Integer batchStrength ;
	
}
	
	
	
