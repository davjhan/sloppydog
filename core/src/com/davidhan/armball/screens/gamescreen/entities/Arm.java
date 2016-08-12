package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.constants.GameConst;
import com.davidhan.armball.screens.gamescreen.box2d.BodyFactory;

import java.util.List;

/**
 * name: Arm
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Arm extends BodyEntity {
    public static final String TAG = "arm";
    public static final String SHAFT = "arm_shaft";
    public static final String HAND = "arm_hand";
    public static final String SHOULDER = "shoulder";
    Body shoulder;
    Body hand;
    List<Body> links;
    public Arm(IApp iApp, World world) {
        super(iApp);
        makeBody(world);
    }

   

    private void makeBody(World world) {
        shoulder = BodyFactory.createShoulder(world);
        shoulder.setTransform(Display.WORLD_HALF_WIDTH, GameConst.Arm.SHOULDER_Y,0);

        links = BodyFactory.createArm(world,shoulder);
        links.get(links.size()-1).applyLinearImpulse(new Vector2(100,0),links.get(links.size()-1).getWorldCenter(),true);

        hand = BodyFactory.createHand(world,links.get(links.size()-1));

        shoulder.setUserData(this);
        for(Body l: links){
            l.setUserData(this);
        }
        hand.setUserData(this);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    public List<Body> getLinks() {
        return links;
    }

    @Override
    public void act(float delta) {
    }

    public Body getShoulder() {
        return shoulder;
    }
    public void move (int dire){
        Vector2 vec= new Vector2(0,GameConst.Arm.IMPULSE_POWER);
        vec.setAngle(90-30*dire);
        hand.applyLinearImpulse(vec,hand.getWorldCenter(),true);
    }
    public void gotoTarget(Vector2 target) {
        Vector2 vec= new Vector2(target);
        vec.sub(hand.getPosition());
        vec.setLength(GameConst.Arm.IMPULSE_POWER);
        hand.applyLinearImpulse(vec,hand.getWorldCenter(),true);
    }
    public void jump() {
        hand.applyLinearImpulse(new Vector2(0,GameConst.Arm.IMPULSE_POWER),hand.getWorldCenter(),true);
    }
}
