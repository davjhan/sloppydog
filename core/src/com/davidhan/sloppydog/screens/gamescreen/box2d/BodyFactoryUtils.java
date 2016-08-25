package com.davidhan.sloppydog.screens.gamescreen.box2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * name: BodyFactoryUtils
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BodyFactoryUtils {
    private static float pi = 3.14159f;

    public static RevoluteJointDef getLinkRevoluteJointDef(float maxAngleDeg){
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.lowerAngle = -maxAngleDeg*MathUtils.degRad; // -90 degrees
        revoluteJointDef.upperAngle = maxAngleDeg*MathUtils.degRad; // 45 degrees
        revoluteJointDef.collideConnected = false;
        revoluteJointDef.localAnchorB.set(0, -com.davidhan.sloppydog.constants.GameConst.Arm.LINK_HALF_LENGTH);
        revoluteJointDef.enableLimit = true;
        revoluteJointDef.enableMotor = true;

        return revoluteJointDef;
    }


    public static FixtureDef getArmFixtureDef(){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.2f;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0.9f;

        return fixtureDef;
    }
    public static void joinBodiesWithRevolute(World world,RevoluteJointDef jointDef,Body bodyA,Body bodyB,Vector2 jointPos){
        jointDef.initialize(bodyA, bodyB, jointPos);
        world.createJoint(jointDef);
    }
    public static void joinBodiesWithRevolute(World world,RevoluteJointDef jointDef,Body bodyA,Body bodyB,Vector2 localA,Vector2 localB){
        jointDef.initialize(bodyA, bodyB, localA);
        jointDef.localAnchorA.set(localA);
        jointDef.localAnchorB.set(localB);
        world.createJoint(jointDef);
    }
    public static Body createPillBody(World world,float radius,float halfLength,Object shaftUserData,Filter filter){
        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //createBody
        Body body = world.createBody(bodyDef);

        //define shapes;
        CircleShape knobShape = new CircleShape();
        knobShape.setRadius(radius);

        PolygonShape linkShape = new PolygonShape();
        linkShape.setAsBox(
                radius,
                halfLength
        );

        //Make Shaft
        fixtureDef.shape = linkShape;
        Fixture shaft= body.createFixture(fixtureDef);
        shaft.setUserData(shaftUserData);

        knobShape.setPosition(new Vector2(0,-halfLength));
        fixtureDef.shape = knobShape;
        Fixture topKnob= body.createFixture(fixtureDef);
        topKnob.setUserData(shaftUserData);

        knobShape.setPosition(new Vector2(0, halfLength));
        Fixture botKnob= body.createFixture(fixtureDef);
        botKnob.setUserData(shaftUserData);

        linkShape.dispose();
        knobShape.dispose();


        if(filter != null){
            shaft.setFilterData(filter);
            topKnob.setFilterData(filter);
            botKnob.setFilterData(filter);
        }
        return body;
    }

    public static void setRevolutionMax(RevoluteJointDef revDef,float revolutionMaxDeg) {
        setRevolutionMax(revDef,revolutionMaxDeg,0);
    }
    public static void setRevolutionMax(RevoluteJointDef revDef,float revolutionMaxDeg,float offsetDeg) {
        revDef.lowerAngle = (revolutionMaxDeg-offsetDeg)* MathUtils.degRad;
        revDef.upperAngle = (revolutionMaxDeg+offsetDeg)* MathUtils.degRad;
    }
}
