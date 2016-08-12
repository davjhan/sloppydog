package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.GameConst;

/**
 * name: Player
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Player extends PhysicalEntity {
    public static final String TORSO = "torso";
    public static final String FEET = "feet";
    public static final String HEAD = "head";
    public static final String TAG = "player";
    boolean keyDown = false;
    boolean onGround;
    int facing = 1;
    Vector2 tmp;
    Vector2 movementImpulse = new Vector2(GameConst.Player.VELOCITY,0);
    public Player(IApp iApp,Body body) {
        super(iApp,body);
    }

    public void move(int dire){
        if(dire != facing){
            setHorizontalVelZero();
        }
        facing = dire;
        //body.setLinearVelocity(movementImpulse.setAngle(90-90*dire));
        body.applyLinearImpulse(movementImpulse.setAngle(90-90*dire), body.getWorldCenter(), true);
        keyDown = true;
    }

    private void setHorizontalVelZero() {
        tmp = body.getLinearVelocity();
        tmp.x = 0;
        body.setLinearVelocity(tmp);
    }

    @Override
    public void act(float delta) {
        tmp = body.getLinearVelocity();
        if(tmp.x > 0){
            tmp.x = Math.min(tmp.x,GameConst.Player.X_DAMPING);
        }else if(tmp.x < 0){
            tmp.x = Math.max(tmp.x,-GameConst.Player.X_DAMPING);
        }
        if(!keyDown){
            setHorizontalVelZero();
        }
        body.setLinearVelocity(tmp);
        keyDown = false;
        super.act(delta);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void jump() {
        if(onGround) {
            Gdx.app.log("tttt Player", "jump: " + "jumping");
            body.applyLinearImpulse(new Vector2(0, GameConst.Player.JUMP_STRNGTH),body.getWorldCenter(), true);
        setOnGround(false);
        }
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
