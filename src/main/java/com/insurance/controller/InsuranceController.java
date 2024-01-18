package com.insurance.controller;

import com.insurance.entity.User;
import com.insurance.payload.UserDto;
import com.insurance.services.InsuranceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceServices services;

    @PostMapping("/create")
    public ResponseEntity<String> createInsuranceOfUser(@RequestBody UserDto userDto){
        return services.createInsuranceOfUser(userDto);

    }

    @PutMapping("/upgrade")
    public ResponseEntity<String> upgradeInsuranceToLong_Term(@RequestBody UserDto userDto){
        return services.upgradeInsuranceToLong_Term(userDto);
    }
}
