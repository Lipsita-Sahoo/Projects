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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="village_master")

public class Village implements Serializable {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="village_name")
	private String villageName;
	
	@ManyToOne
	@JoinColumn(name="panchayat_id")
	private Panchayat panchayat;
	
	@ManyToOne
	@JoinColumn(name="block_id")
	private Block block;
	
	
	
}
