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
    private int goalID;
    private LocalDate dateOfBirth;

    public UserAccount(String name, String email, String password, int genderID, int goalID, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.genderID = genderID;
        this.goalID = goalID;
        this.dateOfBirth = dateOfBirth;
    }
}
