package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.davidhan.sloppydog.screens.gamescreen.box2d.GameContactListener;
import com.davidhan.sloppydog.screens.gamescreen.entities.Apple;
import com.davidhan.sloppydog.screens.gamescreen.entities.BodyEntity;
import com.davidhan.sloppydog.screens.gamescreen.entities.Dog;
import com.davidhan.sloppydog.screens.gamescreen.entities.Wall;


/**
 * name: GameContactListener
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerContactListener extends GameContactListener {
    SinglePlayerGame singlePlayerGame;
    BodyEntity beATemp;
    BodyEntity beBTemp;
    public SinglePlayerContactListener(SinglePlayerGame singlePlayerGame){
        super();
        this.singlePlayerGame = singlePlayerGame;
    }
    @Override
    public void beginContact(Contact contact) {
      //  PhysicalEntity a = (PhysicalEntity)contact.getFixtureA().getBody().getUserData();
      //  PhysicalEntity b = (PhysicalEntity)contact.getFixtureB().getBody().getUserData();
        if(between(contact, Dog.TAG, Apple.TAG)){
            if(oneFixtureIsTag(contact, Dog.HEAD)){
                    singlePlayerGame.onAppleEaten((Apple)getEntityByTag(contact,Apple.TAG));

            }
        }

        if(oneFixtureIsTag(contact, Dog.HEAD)){
            Dog arm= (Dog) getEntityByTag(contact, Dog.TAG);
            //Gdx.app.log("tttt GameContactListener", "vel len "+arm.getHand().getLinearVelocity().len2());
            if(arm.getHead().getLinearVelocity().len2() > 30*30){
               // Gdx.app.log("tttt GameContactListener", "shake: ");
                singlePlayerGame.shakeScreen(4);
            }
        }


    }

    @Override
    public void endContact(Contact contact) {

        if(between(contact, Wall.TAG, Apple.TAG)){
            Apple apple = (Apple) getEntityByTag(contact,Apple.TAG);
            apple.hitWall();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
