package com.insurance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    @Id
    private Long userId;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long mobileNo;

    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insuranceId", referencedColumnName = "insuranceId" )
    private InsuranceDetails insuranceDetails;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;


}
