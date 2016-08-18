package com.davidhan.sloppydog.uireusables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * name: GameGroup
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameGroup extends Group {
    public void spawn(Actor entity){
        addActor(entity);
    }
    public void spawn(Actor entity, float x, float y){
        entity.setPosition(x,y);
        addActor(entity);
    }
    public void spawn(Actor entity,float x, float y,int align){
        entity.setPosition(x,y,align);
        addActor(entity);
    }

}
