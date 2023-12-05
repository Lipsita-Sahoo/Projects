package com.csm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.Join;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="citizen_master")

public class Citizen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="citizen_id")
	private Integer citizenId;
	
	@Column(name="citizen_name")
	private String citizenName;
	
	@ManyToOne
	@JoinColumn(name="block_id")
	private Block block;
	
	@ManyToOne
	@JoinColumn(name="panchayat_id")
	private Panchayat panchayat;
	
	@ManyToOne
	@JoinColumn(name="village_id")
	private Village village;
	
	@Column(name = "citizen_age")
	private Integer citizenAge;
	
	@Column(name="citizen_annual_income")
	private Integer citizenAnnualIncome;
}
