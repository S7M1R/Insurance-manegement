package com.insurance.payload;

import com.insurance.entity.InsuranceDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String email;
    private long mobileNo;
    private String password;
    private InsuranceDetails insuranceDetails;
    private String insuranceType;
}
