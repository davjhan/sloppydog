package com.davidhan.armball.screens.gamescreen.box2d;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.davidhan.armball.constants.GameConst;

/**
 * name: BodyFactoryUtils
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BodyFactoryUtils {
    private static float pi = 3.14159f;

    public static RevoluteJointDef getLinkRevoluteJointDef(){
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.lowerAngle = -0.075f * pi; // -90 degrees
        revoluteJointDef.upperAngle = 0.075f * pi; // 45 degrees
        revoluteJointDef.collideConnected = false;
        revoluteJointDef.localAnchorB.set(0, -GameConst.Arm.LINK_HALF_LENGTH);
        revoluteJointDef.enableLimit = true;
        revoluteJointDef.enableMotor = true;

        return revoluteJointDef;
    }


    public static FixtureDef getArmFixtureDef(){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.2f;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0.9f;

        return fixtureDef;
    }
}
