package com.davidhan.armball.constants;

/**
 * name: GameSizes
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameConst {
    public class Physics{
        public static final float GRAVITY = -40;
        public static final float BOUNDS_HALF_THICKNESS = 1;
        public static final float GROUND_TOP = 10;
        public static final float BALL_INITIAL_Y = 20;
        public static final float BALL_INITIAL_X = 18;
    }
    public class Hoop{
        public static final float RADIUS = 0.5f;
        public static final float HOOP_DISTANCE = 4;
        public static final float Y = 35;

        public static final float NET_HEIGHT = 0.1f;
    }
    public class Player{
        public static final float HEAD_RADIUS = 1.5f;
        public static final float TORSO_HEIGHT = 1.5f;
        public static final float STARTING_X = 6;
        public static final float VELOCITY = 500;
        public static final float FEET_HEIGHT = 0.1f;


        public static final float JUMP_STRNGTH = 1250;
        public static final float X_DAMPING = 15;
    }
    public class Arm{
        public static final float NUM_LINKS = 24;
        public static final float SHOULDER_RADIUS = 1;
        public static final float ARM_THICKNESS = 0.6f;
        public static final float LINK_HALF_LENGTH = 0.6f;
        public static final float SHOULDER_Y = Physics.GROUND_TOP;
        public static final float HAND_RADIUS = 1f;
        public static final float IMPULSE_POWER = 30;

    }

    public class Ball {
        public static final float RADIUS = 1.5f;
        public static final float STARTING_X = 6;
        public static final float STARTING_Y = 26;
        public static final float VELOCITY = 25;
        public static final float TORSO_HIT_REBOUND = 40;

        public static final float HEAD_HIT_REBOUND = 20;
    }
    public class  UX{
        public static final int HAPTIC_BUZZ_DUR = 10;
    }

    public class TARGET {
        public static final float RADIUS = 1f;
    }
}
