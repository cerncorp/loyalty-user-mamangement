package com.example.usermanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -4054162385300685878L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_name")
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="first_name_khr")
    private String firstNameKhr;
    @Column(name="last_name_khr")
    private String lastNameKhr;
    @Column(name="identify_type")
    private String identifyType;
    @Column(name="id_number")
    private String idNumber;

    @Column(name="dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    @Column(name="gender")
    private String gender;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name="email")
    private String email;

}
