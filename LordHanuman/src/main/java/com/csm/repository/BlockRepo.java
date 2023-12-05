package com.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csm.model.Block;

@Repository

public interface BlockRepo extends JpaRepository<Block, Integer> {

}
