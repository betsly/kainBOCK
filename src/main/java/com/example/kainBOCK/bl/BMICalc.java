package com.example.kainBOCK.bl;

import com.example.kainBOCK.pojo.bmi;

public class BMICalc {

    /**
     *
     * @param height
     * @param weight
     * @return
     */
    public static double getBMI(int height, double weight) {
        double value = 0;
        value = weight/(Math.pow(height/100.0, 2));
        return value;
    }
}
