package com.example.kainBOCK.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
    private Date dateOfBirth;

    public UserAccount(String name, String email, String password, int genderID, String goal, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.genderID = genderID;
        this.goal = goal;
        this.dateOfBirth = dateOfBirth;
    }
}
