package com.insurance.services;

import com.insurance.entity.Gender;
import com.insurance.entity.InsuranceDetails;
import com.insurance.entity.InsuranceType;
import com.insurance.entity.User;
import com.insurance.payload.UserDto;
import com.insurance.repository.InsuranceDetailsRepository;
import com.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class InsuranceServicesIMPL implements InsuranceServices {

    @Autowired
    private InsuranceRepository repository;

    @Autowired
    InsuranceDetailsRepository detailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> createInsuranceOfUser(UserDto userDto) {

        User user = new User();
        long userId = Math.abs(UUID.randomUUID().getMostSignificantBits());
        long insuranceId = Math.abs(UUID.randomUUID().getMostSignificantBits());
        user.setUserId(userId);
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGender(Gender.valueOf(userDto.getGender().toUpperCase()));
        user.setBirthDate(userDto.getBirthDate());
        user.setEmail(userDto.getEmail());
        user.setMobileNo(userDto.getMobileNo());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        InsuranceDetails insurance = new InsuranceDetails();
        insurance.setInsuranceId(insuranceId);
        insurance.setUserId(userId);
        insurance.setCreationDate(Date.valueOf(LocalDate.now()));
        // Calculates the age based on the provided birth_date
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = userDto.getBirthDate().toLocalDate();
        Period calculation = Period.between(birthDate, currentDate);
        int age = calculation.getYears();
        if (userDto.getInsuranceType().equals("LIFE_INSURANCE")){
            if (age < 30){
                LocalDate expiryDate = currentDate.plusYears(30 - age);
                insurance.setExpiryDate(Date.valueOf(expiryDate));
                insurance.setInsuranceDetailsForType(InsuranceType.LIFE_INSURANCE);
            } else if (age > 30) {
                return new ResponseEntity<>("you are not applicable for 'LIFE_INSURANCE' Insurance Policy please choose another policy ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else if (userDto.getInsuranceType().equals("LONG_TERM_LIFE_INSURANCE")) {
            LocalDate expiryDate = currentDate.plusYears(100 - age);
            insurance.setExpiryDate(Date.valueOf(expiryDate));
            insurance.setInsuranceDetailsForType(InsuranceType.LONG_TERM_LIFE_INSURANCE);
        }
        user.setInsuranceDetails(insurance);
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Insurance Created");
    }

    @Override
    public ResponseEntity<String> upgradeInsuranceToLong_Term(UserDto userDto) {
        String email = userDto.getEmail();
        String userName = userDto.getUserName();
        long mobileNo = userDto.getMobileNo();

        User user = repository.findByEmailOrUserNameOrMobileNo(email, userName, mobileNo);

        if (user != null) {
            Long userId = user.getUserId();
            InsuranceDetails insuranceDetails = detailsRepository.findByUserId(userId);
            if (insuranceDetails != null){
                LocalDate currentDate = LocalDate.now();
                LocalDate birthDate = user.getBirthDate().toLocalDate();
                Period calculation = Period.between(birthDate, currentDate);
                int age = calculation.getYears();
                LocalDate expiryDate = currentDate.plusYears(100 - age);
                insuranceDetails.setExpiryDate(Date.valueOf(expiryDate));
                insuranceDetails.setInsuranceDetailsForType(InsuranceType.LONG_TERM_LIFE_INSURANCE);
                return new ResponseEntity<>("policy upgraded ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("you don't have subscription of any insurance policy", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }


}
