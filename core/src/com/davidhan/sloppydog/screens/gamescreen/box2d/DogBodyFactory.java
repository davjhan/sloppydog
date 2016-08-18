package com.davidhan.sloppydog.screens.gamescreen.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.entities.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * name: DogBodyFactory
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class DogBodyFactory {
    public static List<Body> createDogBody(World world,Object userData) {
        List<Body> links = new ArrayList<Body>();
        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(GameConst.Dog.REVOLUTE_JOINT_MAX_ANGLE);
        //---
        Body prevBody = null;
        Vector2 joinSpot = new Vector2();
        for (int i = 0; i < GameConst.Dog.Torso.NUM_LINKS; i++) {
            //create Link Body.
            Body linkBody = BodyFactoryUtils.createPillBody(world,
                    GameConst.Dog.Torso.RADIUS,
                    GameConst.Dog.Torso.LINK_HALF_LENGTH,
                    Dog.SHAFT,
                    CollisionGroups.FILTER_DOG_TORSO());

            linkBody.setTransform(
                    0,
                    - GameConst.Dog.Torso.LINK_HALF_LENGTH
                            - (i * GameConst.Dog.Torso.LINK_HALF_LENGTH * 2), 0);

            if(i > 0) {
                BodyFactoryUtils.joinBodiesWithRevolute(world,revoluteJointDef,prevBody,linkBody,joinSpot);
            }

            prevBody = linkBody;
            joinSpot.set(
                    linkBody.getPosition().x,
                    linkBody.getPosition().y - GameConst.Dog.Torso.LINK_HALF_LENGTH);
            links.add(linkBody);
            linkBody.setUserData(userData);
            linkBody.setLinearDamping(0.1f);
        }

        return links;
    }
    public static Body createHead(World world, Body firstLink) {


        BodyDef headBodyDef = new BodyDef();
        headBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body handBody = world.createBody(headBodyDef);
        handBody.setTransform(
                firstLink.getPosition().x,
                firstLink.getPosition().y+ GameConst.Dog.Torso.LINK_HALF_LENGTH,0);

        // filterData.maskBits = 31;

        CircleShape handShape = new CircleShape();
        handShape.setRadius(GameConst.Dog.Head.RADIUS);

        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        fixtureDef.density=5;
        fixtureDef.shape = handShape;
        Fixture head = handBody.createFixture(fixtureDef);
        head.setUserData(Dog.HEAD);
        head.setFilterData(CollisionGroups.FILTER_DOG_TORSO());
      //  Fixture handSensor = handBody.createFixture(fixtureDef);
      // handSensor.setUserData(Dog.HEAD);
       // handSensor.setSensor(true);
        //hand.setFilterData(filterData);



        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(GameConst.Dog.REVOLUTE_JOINT_MAX_ANGLE);
        revoluteJointDef.initialize(firstLink, handBody, firstLink.getPosition().cpy().add(0, GameConst.Dog.Torso.LINK_HALF_LENGTH));

        revoluteJointDef.localAnchorA.x = 0;
        revoluteJointDef.localAnchorA.y = GameConst.Dog.Torso.LINK_HALF_LENGTH;

        revoluteJointDef.localAnchorB.x = 0;
        revoluteJointDef.localAnchorB.y = -GameConst.Dog.Torso.LINK_HALF_LENGTH;
        world.createJoint(revoluteJointDef);
        return handBody;
    }
    public static Body createTail(World world, Body firstLink) {


        BodyDef headBodyDef = new BodyDef();
        headBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body handBody = world.createBody(headBodyDef);
        handBody.setTransform(
                firstLink.getPosition().x,
                firstLink.getPosition().y- GameConst.Dog.Torso.LINK_HALF_LENGTH,0);

        // filterData.maskBits = 31;

        CircleShape handShape = new CircleShape();
        handShape.setRadius(GameConst.Dog.Tail.RADIUS);

        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        fixtureDef.density=5;
        fixtureDef.shape = handShape;
        Fixture head = handBody.createFixture(fixtureDef);
        head.setUserData(Dog.TAIL);

        Filter filter= CollisionGroups.FILTER_DOG_TORSO();
        filter.maskBits &= ~CollisionGroups.DOG_TORSO;
        head.setFilterData(filter);
        //  Fixture handSensor = handBody.createFixture(fixtureDef);
        // handSensor.setUserData(Dog.HEAD);
        // handSensor.setSensor(true);
        //hand.setFilterData(filterData);



        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(GameConst.Dog.REVOLUTE_JOINT_MAX_ANGLE);
        revoluteJointDef.initialize(firstLink, handBody, firstLink.getPosition().cpy().add(0, GameConst.Dog.Torso.LINK_HALF_LENGTH));

        revoluteJointDef.localAnchorA.x = 0;
        revoluteJointDef.localAnchorA.y = -GameConst.Dog.Torso.LINK_HALF_LENGTH;

        revoluteJointDef.localAnchorB.x = 0;
        revoluteJointDef.localAnchorB.y = GameConst.Dog.Torso.LINK_HALF_LENGTH;
        world.createJoint(revoluteJointDef);
        return handBody;
    }

    public static List<Body> createArm(World world, Body shoulder,Object userData) {
        List<Body> links = new ArrayList<Body>();
        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef(GameConst.Dog.REVOLUTE_JOINT_MAX_ANGLE);
        //---
        Body prevBody = null;
        Vector2 joinSpot = new Vector2(shoulder.getPosition());
        for (int i = 0; i < GameConst.Dog.Arm.NUM_LINKS; i++) {
            //create Link Body.
            Body linkBody = BodyFactoryUtils.createPillBody(world,
                    GameConst.Dog.Arm.RADIUS,
                    GameConst.Dog.Arm.LINK_HALF_LENGTH,
                    Dog.SHAFT,
                    CollisionGroups.FILTER_DOG_EXTRA());
            linkBody.setTransform(
                    shoulder.getPosition().x,
                    shoulder.getPosition().y- GameConst.Dog.Torso.LINK_HALF_LENGTH
                            - (i * GameConst.Dog.Arm.LINK_HALF_LENGTH * 2), 0);

            if(i > 0){
                BodyFactoryUtils.joinBodiesWithRevolute(world,revoluteJointDef,prevBody,linkBody,joinSpot);
            }

            joinSpot.set(
                    linkBody.getPosition().x,
                    linkBody.getPosition().y - GameConst.Dog.Arm.LINK_HALF_LENGTH);
            linkBody.setUserData(userData);
            prevBody = linkBody;

            links.add(linkBody);
        }

        joinSpot.set(
                shoulder.getPosition().x,
                shoulder.getPosition().y);
        BodyFactoryUtils.setRevolutionMax(revoluteJointDef, 45,-90);
        BodyFactoryUtils.joinBodiesWithRevolute(world,revoluteJointDef,shoulder, links.get(0),new Vector2(),new Vector2(0,GameConst.Dog.Arm.LINK_HALF_LENGTH));

        return links;
    }
}
