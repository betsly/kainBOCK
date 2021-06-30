package com.example.kainBOCK.bl;

import com.example.kainBOCK.pojo.bmi;

/**
 * @author jiazhou
 * @since 23.03.2021
 *
 * Klasse um BMI zu berechnen
 *
 */
public class BMICalc {

    /**
     * claculates BMI value
     *
     * @param height
     * @param weight
     * @return
     */
    public static double getBMI(int height, double weight) {
        double value = 0;
        if(height == 0 || weight == 0)
            throw new IllegalArgumentException("height or weight can't be 0");
        value = weight/(Math.pow(height/100.0, 2));
        return value;
    }
}
