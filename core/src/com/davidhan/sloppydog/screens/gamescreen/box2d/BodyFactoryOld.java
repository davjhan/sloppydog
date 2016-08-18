package com.davidhan.sloppydog.screens.gamescreen.box2d;

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
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.entities.Arm;

import java.util.ArrayList;
import java.util.List;

/**
 * name: BodyFactoryOld
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BodyFactoryOld {
    public static List<Body> createArm(World world) {
        List<Body> links = new ArrayList<Body>();
        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();

        BodyDef linkBodyDef = new BodyDef();
        linkBodyDef.type = BodyDef.BodyType.DynamicBody;

        CircleShape knob = new CircleShape();
        knob.setRadius(GameConst.Arm.ARM_RADIUS);
        PolygonShape linkShape = new PolygonShape();
        linkShape.setAsBox(
                GameConst.Arm.ARM_RADIUS,
                GameConst.Arm.LINK_HALF_LENGTH
        );
        fixtureDef.shape = linkShape;


        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(0.75f);
        revoluteJointDef.lowerAngle = -(float)Math.toRadians(360);
        revoluteJointDef.upperAngle = 0;
        //---
        Body prevBody = null;
        Vector2 joinSpot = new Vector2();

        Filter filterData = new Filter();
//        filterData.categoryBits= CollisionGroups.ARM_LINK_0;
        filterData.maskBits=1;

        // filterData0.groupIndex = -1;
        for (int i = 0; i < GameConst.Arm.NUM_LINKS; i++) {
            Body linkBody = world.createBody(linkBodyDef);
            if(i == 1){
                revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(0.75f);
                revoluteJointDef.localAnchorA.x = 0;
                revoluteJointDef.localAnchorA.y = GameConst.Arm.LINK_HALF_LENGTH;

            }

            fixtureDef.shape = linkShape;
            linkBody.setTransform(
                    GameConst.Arm.P0_STARTING_X,
                    GameConst.Arm.P0_STARTING_Y
                            - GameConst.Arm.LINK_HALF_LENGTH
                            - (i * GameConst.Arm.LINK_HALF_LENGTH * 2), 0);
            Fixture shaft= linkBody.createFixture(fixtureDef);
            shaft.setFilterData(filterData);
            shaft.setUserData(Arm.SHAFT);

            knob.setPosition(new Vector2(0,-GameConst.Arm.LINK_HALF_LENGTH));
            fixtureDef.shape = knob;
            Fixture topKnob= linkBody.createFixture(fixtureDef);
            topKnob.setFilterData(filterData);
            topKnob.setUserData(Arm.SHAFT);

            knob.setPosition(new Vector2(0, GameConst.Arm.LINK_HALF_LENGTH));
            Fixture botKnob= linkBody.createFixture(fixtureDef);
            botKnob.setFilterData(filterData);
            botKnob.setUserData(Arm.SHAFT);

            if(i > 0) {
                revoluteJointDef.initialize(prevBody, linkBody, joinSpot);
                world.createJoint(revoluteJointDef);
            }

            links.add(linkBody);
            prevBody = linkBody;
            joinSpot.set(
                    linkBody.getPosition().x,
                    linkBody.getPosition().y - GameConst.Arm.LINK_HALF_LENGTH);
            if(i % 2 == 0){
                //filterData.categoryBits= CollisionGroups.ARM_LINK_0;
                filterData.maskBits=31;
              //  filterData.maskBits&= ~CollisionGroups.ARM_LINK_0;
            }else{
              //  filterData.categoryBits= CollisionGroups.ARM_LINK_1;
                filterData.maskBits=31;
               // filterData.maskBits&= ~CollisionGroups.ARM_LINK_1;
            }
        }

        return links;
    }

    public static Body createHand(World world, Body firstLink) {


        BodyDef handBodyDef = new BodyDef();
        handBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body handBody = world.createBody(handBodyDef);
        handBody.setTransform(
                firstLink.getPosition().x,
                firstLink.getPosition().y+ GameConst.Arm.LINK_HALF_LENGTH,0);

        Filter filterData  =new Filter();
        filterData.categoryBits = CollisionGroups.HAND;
        // filterData.maskBits = 31;

        CircleShape handShape = new CircleShape();
        handShape.setRadius(GameConst.Arm.HAND_RADIUS);

        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        fixtureDef.density=5;
        fixtureDef.shape = handShape;
        Fixture hand = handBody.createFixture(fixtureDef);
        Fixture handSensor = handBody.createFixture(fixtureDef);
        handSensor.setUserData(Arm.HAND);
        handSensor.setSensor(true);
        hand.setFilterData(filterData);



        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(0.75f);
        revoluteJointDef.initialize(firstLink, handBody, firstLink.getPosition().cpy().add(0, GameConst.Arm.LINK_HALF_LENGTH));

        revoluteJointDef.localAnchorA.x = 0;
        revoluteJointDef.localAnchorA.y = GameConst.Arm.LINK_HALF_LENGTH;

        revoluteJointDef.localAnchorB.x = 0;
        revoluteJointDef.localAnchorB.y = -GameConst.Arm.HAND_RADIUS;
        world.createJoint(revoluteJointDef);
        return handBody;
    }
}
