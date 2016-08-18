package com.davidhan.sloppydog.constants;

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
    public class Dog{
        public static final float REVOLUTE_JOINT_MAX_ANGLE = 0.075f;

        public class Torso {
            public static final float NUM_LINKS = 10;
            public static final float RADIUS = 1f;
            public static final float LINK_HALF_LENGTH = 0.6f;
        }
        public class Head {
            public static final float RADIUS = 1.5f;
        }
        public class Tail {
            public static final float RADIUS = 1.5f;
        }
        public class Arm{
            public static final float NUM_LINKS = 6;
            public static final float RADIUS = 0.3f;
            public static final float LINK_HALF_LENGTH = 0.4f;
        }
        public class Butt{
            public static final float NUM_LINKS = 5;
            public static final float RADIUS = 1.5f;
            public static final float HALF_LENGTH = 0.75f;
        }
        public static final float STARTING_Y= 9;
        public static final float STARTING_X= Display.WORLD_HALF_WIDTH+7;
        public static final float IMPULSE_POWER = 50;



    }

    public class World{
        public static final float SCALE = 10;
        public static final int WIDTH = 27;
        public static final float HALF_WIDTH = 13.5f;
        public static final int HEIGHT = 48;
        public static final float HALF_HEIGHT = 24;
    }
    public class BOUNDS{
        public static final float DOOR_HALF_WIDTH = Dog.Torso.RADIUS+1f;
        public static final float WALL_THICKNESS = 9;
        public static final String WALL_TAG = "wall";
        public static final String DOOR_TAG = "door";

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

    public class Hud {
        public static final float BOTTOM = 1f;

        public class HungerMeter {
            public static final float HEIGHT = 30;
            public static final float WIDTH =Display.WIDTH;
            public static final float PAD = 4;
        }
    }
}
