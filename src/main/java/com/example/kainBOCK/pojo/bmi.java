package com.example.kainBOCK.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bmi {

    private Date birthdate;
    private double value;
    private char gender;
    private double height;
    private double weight;
    private int user_id;

}
