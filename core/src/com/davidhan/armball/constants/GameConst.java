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
        public static final float GRAVITY = 0;
        public static final float BOUNDS_HALF_THICKNESS = 1;
        public static final float GROUND_TOP = 0;
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
        public static final float NUM_LINKS = 30;
        public static final float ARM_RADIUS = 1f;
        public static final float LINK_HALF_LENGTH = 0.5f;

        public static final float HAND_RADIUS = 1f;
        public static final float STARTING_Y_DIST = 9;
        public static final float STARTING_X_DIST = 7;
        public static final float P0_STARTING_Y = STARTING_Y_DIST;
        public static final float P0_STARTING_X = Display.WORLD_HALF_WIDTH+STARTING_X_DIST;
        public static final float IMPULSE_POWER = 60;

        public static final float P1_STARTING_X = Display.WORLD_HALF_WIDTH-STARTING_X_DIST;
        public static final float P1_STARTING_Y = Display.WORLD_HEIGHT-STARTING_Y_DIST+3;
    }
    public class DOG{
        public static final float NUM_LINKS = 30;
        public static final float RADIUS = 1f;
        public static final float LINK_HALF_LENGTH = 0.5f;

        public static final float HEAD_RADIUS = 1f;
        public static final float STARTING_Y_DIST = 9;
        public static final float STARTING_X_DIST = 7;
        public static final float IMPULSE_POWER = 50;

    }
    public class BOUNDS{
        public static final float DOOR_HALF_WIDTH = Arm.ARM_RADIUS+0.1f;
        public static final float WALL_THICKNESS = 9;

    }
    public class Ball {
        public static final float RADIUS = 1f;
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
