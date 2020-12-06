package com.daar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daar.model.CV;

@Repository
public interface CVRepository extends JpaRepository<CV, Long>{

}
