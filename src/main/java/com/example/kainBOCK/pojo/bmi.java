package com.example.kainBOCK.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bmi {
    private LocalDate date;
    private double value;
    private int height;
    private double weight;
    private int user_id;
}
