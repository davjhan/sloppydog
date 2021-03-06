package com.davidhan.sloppydog.screens.gamescreen.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.davidhan.sloppydog.screens.gamescreen.entities.BodyEntity;

/**
 * name: GameContactListener
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class GameContactListener implements ContactListener {
   private static BodyEntity beATemp;
   private static BodyEntity beBTemp;
    private static String tempStringA;
    private static String tempStringB;
    public static boolean oneFixtureIsTag(Contact contact, String tag) {
        return (contact.getFixtureA().getUserData() != null
                && contact.getFixtureA().getUserData().equals(tag))
                || (contact.getFixtureB().getUserData() != null
                && contact.getFixtureB().getUserData().equals(tag));
    }
    public static boolean oneBodyIsTag(com.davidhan.sloppydog.screens.gamescreen.entities.PhysicalEntity a, com.davidhan.sloppydog.screens.gamescreen.entities.PhysicalEntity b, String tag) {
        return a.getTag().equals(tag) || b.getTag().equals(tag);
    }
    public static BodyEntity getEntityByTag(Contact contact, String tag) {
        if(contact.getFixtureA().getBody().getUserData() instanceof BodyEntity){
            beATemp = (BodyEntity)contact.getFixtureA().getBody().getUserData();
        }else{
            return (BodyEntity)contact.getFixtureB().getBody().getUserData();
        }
        if(contact.getFixtureB().getBody().getUserData() instanceof BodyEntity){
            beBTemp = (BodyEntity)contact.getFixtureB().getBody().getUserData();
        }else{
            return (BodyEntity)contact.getFixtureA().getBody().getUserData();
        }
        return beATemp.getTag().equals(tag)?beATemp: beBTemp;
    }
    public static Fixture getFixtureByTag(Contact contact, String tag) {
        return contact.getFixtureA().getUserData().equals(tag)? contact.getFixtureA(): contact.getFixtureB();
    }

    public static boolean between(Contact contact,String tag1, String tag2) {
        if(contact.getFixtureA().getBody().getUserData() == null || contact.getFixtureB().getBody().getUserData() == null){
            return false;
        }
        if(contact.getFixtureA().getBody().getUserData() instanceof String){
            tempStringA = (String)contact.getFixtureA().getBody().getUserData();
        }else{
            tempStringA = ((BodyEntity)contact.getFixtureA().getBody().getUserData()).getTag();
        }
        if(contact.getFixtureB().getBody().getUserData() instanceof String){
            tempStringB = (String)contact.getFixtureB().getBody().getUserData();
        }else{
            tempStringB = ((BodyEntity)contact.getFixtureB().getBody().getUserData()).getTag();
        }
        return((tempStringA.equals(tag1) && tempStringB.equals(tag2)) ||
                (tempStringA.equals(tag2) && tempStringB.equals(tag1)));
    }
}
