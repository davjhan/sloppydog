package com.davidhan.sloppydog.utils;

/**
 * name: NumberUtils
 * desc:
 * date: 2016-08-22
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class NumUtils {
    public static String numPaddedToString(int num, int decimalPlaces,String zeroesColor,String numberColor){
        StringBuilder sb = new StringBuilder();
        sb.append("[#");
        sb.append(zeroesColor);
        sb.append("]");
        for(int i = 0; i < decimalPlaces-String.valueOf(num).length(); i ++){
            sb.append('0');
        }
        sb.append("[#");
        sb.append(numberColor);
        sb.append("]");
        sb.append(num);
        return sb.toString();
    }

}
