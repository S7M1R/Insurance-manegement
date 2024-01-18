package com.insurance.repository;

import com.insurance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<User, Long> {
    User findByEmailOrUserNameOrMobileNo(String email, String userName, Long mobileNo);
}
