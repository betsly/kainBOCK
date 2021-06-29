package com.example.kainBOCK.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAccount {
    private int userID;
    private String name;
    private String email;
    private String password;
    private int genderID;
    private String gender;
    private int goalID;
    private String goal;
    private LocalDate dateOfBirth;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("E dd. MMM yyyy");

    public UserAccount(String name, String email, String password, int genderID, int goalID, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.genderID = genderID;
        this.goalID = goalID;
        this.dateOfBirth = dateOfBirth;
    }

    public UserAccount(String name, String email, String gender, String goal, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.goal = goal;
        this.dateOfBirth = dateOfBirth;
    }

    public String date() { return DTF.format(dateOfBirth); }
}
