package com.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csm.model.Panchayat;
@Repository
public interface PanchayatRepo extends JpaRepository<Panchayat, Integer> {

	@Query("From Panchayat where block.blockId = :blockId")
	List<Panchayat> getPanchayatByBlock(Integer blockId);

}
