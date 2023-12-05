package com.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csm.model.Village;

@Repository
public interface VillageRepo extends JpaRepository<Village, Integer> {

	@Query("From Village where block.blockId = :blockId and panchayat.panchayatId = :panchayatId")
	List<Village> getVillageById(Integer blockId, Integer panchayatId);

}
