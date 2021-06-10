package com.example.kainBOCK.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeStamp {
    private int id;
    private String description;
    private LocalDate date;
}
