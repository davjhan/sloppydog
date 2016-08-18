package com.davidhan.sloppydog.constants;

/**
 * name: SinglePlayerGameRules
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SPGameRules {
    public class Hunger{
        public static final float MAX = 1000;
        public static final float INITIAL = 500;
        public static final float DIMINISH_RATE_PER_FRAME = MAX/(60*15);
        public static final float FOOD_FILL = 200f;
    }
}
