package com.example.kainBOCK.bl;

import com.example.kainBOCK.pojo.bmi;

public class BMICalc {

    /**
     *
     * @param BMI
     * @return
     */
    public static double getBMI(bmi BMI) {
        double value = 0;
        value = BMI.getWeight()/(Math.pow(BMI.getHeight()/100, 2));
        return value;
    }
}
