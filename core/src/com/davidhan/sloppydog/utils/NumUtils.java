package com.davidhan.sloppydog.utils;

/**
 * name: NumberUtils
 * desc:
 * date: 2016-08-22
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class NumUtils {
    public String numPaddedToString(int num, int decimalPlaces,String numberColor){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < decimalPlaces-Math.log10(num) + 1; i ++){
            sb.append('0');
        }
        sb.append("[#");
        sb.append(numberColor);
        sb.append("ff]");
        sb.append(num);
        return sb.toString();
    }

}
