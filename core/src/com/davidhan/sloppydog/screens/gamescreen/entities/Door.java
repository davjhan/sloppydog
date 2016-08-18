package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.box2d.GameContactListener;

/**
 * name: Door
 * desc:
 * date: 2016-08-12
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Door {
    public static final String TAG = "door";
    boolean isOpen = true;
    private Body body;
    boolean isTouching = false;
    public Door(Body body) {
        isOpen = true;
        this.body = body;
    }

    public void checkDoor(World world) {

        if (isOpen) {
            isTouching = false;
            for(Contact contact:world.getContactList()){
                if(GameContactListener.oneFixtureIsTag(contact, GameConst.BOUNDS.DOOR_TAG)){
                    if(!GameContactListener.oneFixtureIsTag(contact,GameConst.BOUNDS.WALL_TAG)){
                        isTouching = true;
                        break;
                    }
                }
            }
            if(!isTouching){
                closeDoor();
            }
        }
    }

    private void closeDoor() {
        isOpen = false;
        body.getFixtureList().get(0).setSensor(false);
        Gdx.app.log("tttt Door", "close: ");
    }
}
