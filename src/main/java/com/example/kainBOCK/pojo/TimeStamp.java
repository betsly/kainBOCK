package com.example.kainBOCK.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeStamp {
    private int id;
    private String description;
    private LocalDateTime date;

    private static final DateTimeFormatter DTFdate = DateTimeFormatter.ofPattern("E, dd. MMM YYYY");
    private static final DateTimeFormatter DTFtime = DateTimeFormatter.ofPattern("HH:mm:ss");

    public String getFormatDate() {
        return DTFdate.format(date);
    }

    public String getFormatTime() {
        return DTFtime.format(date);
    }
}
