package com.insurance.services;

import com.insurance.payload.UserDto;
import org.springframework.http.ResponseEntity;

public interface InsuranceServices {
    ResponseEntity<String> createInsuranceOfUser(UserDto userDto);

    ResponseEntity<String> upgradeInsuranceToLong_Term(UserDto userDto);
}
