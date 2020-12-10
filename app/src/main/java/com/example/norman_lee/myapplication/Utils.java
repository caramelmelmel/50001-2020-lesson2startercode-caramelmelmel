package com.example.norman_lee.myapplication;

import java.math.BigDecimal;

public class Utils {
    static boolean checkValidString(String in){
        Double d = Double.valueOf(in);
        if(d<0) throw new IllegalArgumentException(
                "Please enter a positive value");
        return true;

    }
}
