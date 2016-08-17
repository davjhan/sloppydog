package com.davidhan.armball.screens.gamescreen.hud;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.Random;

/**
 * name: CameraShaker
 * desc:
 * date: 2016-08-12
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class CameraShaker {
    public static Random rand = new Random();
    public static Action getShakeAction(int magnitude){
        float timeMag = 1;
        SequenceAction sequenceAction = new SequenceAction();

        Interpolation interpolation = Interpolation.pow2In;
        int flip = 1;
        Vector2 vec = new Vector2(0,1);
        while(magnitude >= 0.1f){
            vec.setAngle(rand.nextInt(360));
            vec.setLength(magnitude);
            sequenceAction.addAction(
                    Actions.moveTo(vec.x,vec.y,0.03f*timeMag,interpolation)
            );

            flip *= -1;
            if(flip == 1){
                interpolation = Interpolation.pow2Out;
            }else{
                interpolation = Interpolation.pow2In;
            }
            magnitude /= 1.1f;
            timeMag /= 1.1f;
        }
        return sequenceAction;
    }
}
