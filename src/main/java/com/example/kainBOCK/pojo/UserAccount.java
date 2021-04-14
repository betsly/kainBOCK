package com.example.kainBOCK.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAccount {
    private int userID;
    private String name;
    private String email;
    private String password;
    private int genderID;
    private String goal;
    private LocalDate dateOfBirth;
}
