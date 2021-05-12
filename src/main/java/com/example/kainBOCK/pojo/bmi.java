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
    private Date date;
    private double value;
    private double height;
    private double weight;
    private int user_id;

    public bmi(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
}
