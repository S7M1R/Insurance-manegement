package com.insurance.repository;

import com.insurance.entity.InsuranceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceDetailsRepository extends JpaRepository<InsuranceDetails, Long> {
    InsuranceDetails findByUserId(Long userId);
}
