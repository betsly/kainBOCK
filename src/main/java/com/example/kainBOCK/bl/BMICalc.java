package com.example.kainBOCK.bl;

import com.example.kainBOCK.pojo.bmi;

public class BMICalc {

    public static double getBMI(bmi BMI) {

        double value = 0;
        value = BMI.getWeight()/(Math.pow(BMI.getHeight()/100, 2));
        return value;
    }

    public static void main(String[] args) {
        System.out.println(String.format("The BMI is %.2f", getBMI(new bmi(null, 0, 'M', 187, 70))).replace(',', '.'));
        /*
        private LocalDate birthdate;
    private double value;
    private char gender;
    private double height;
    private double weight;
         */
    }

}
