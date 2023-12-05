package com.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csm.model.Citizen;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Integer> {

}
