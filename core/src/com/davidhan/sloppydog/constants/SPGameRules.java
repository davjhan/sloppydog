package com.davidhan.sloppydog.constants;

/**
 * name: SinglePlayerGameRules
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SPGameRules {
    public static class Hunger{
        public static final float MAX = 1000;
        public static final float INITIAL = 750;
        public static final float DIMINISH_RATE_PER_FRAME = MAX/(60*25);
        public static final float FOOD_FILL = 200f;

        public static float getAppleFillAmount(int score ){
            return 50+15*score;
        }
    }
}
