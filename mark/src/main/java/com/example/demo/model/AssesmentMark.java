package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor


public class AssesmentMark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slNo;
	
	private Integer empId;
	
	private Integer mark;


}
