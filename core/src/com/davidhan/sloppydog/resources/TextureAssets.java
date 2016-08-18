package com.davidhan.sloppydog.resources;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.davidhan.sloppydog.constants.AnimConst;

/**
 * name: GraphicAssets
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TextureAssets extends TextureCutter {
    public static final String PACKED_ATLAS = "packed_assets.atlas";
    public NinePatch[] buttonNinePatches;
    public NinePatch[] bgNinePatches;
    public NinePatch[] listItemUnderline;
    public TextureRegion hand[][];
    public TextureRegion dogHeadFrames[][];
    public Animation dogHeadChomp;
    public Animation dogHeadIdle;
    public TextureRegion[][] link;
    public TextureRegion[] dogArm;
    public TextureRegion ball;
    public TextureRegion dogTail;

    public TextureAssets(com.davidhan.sloppydog.resources.Assets assets){
        assets.load(PACKED_ATLAS, TextureAtlas.class);
    }

    public void prepare(com.davidhan.sloppydog.resources.Assets assets) {
        TextureAtlas atlas = assets.get(PACKED_ATLAS,TextureAtlas.class);
        bgNinePatches = cutNinesGroup(atlas,"bg-ninepatches",24,24,9);
        buttonNinePatches =cutNinesGroup(atlas,"btn-ninepatch",30,19,3,3,6);
        listItemUnderline = cutNinesGroup(atlas, "list-item-bg-nine", 12, 12, 3, 3, 3);


        dogHeadFrames = cut(atlas,"dog-head",60,40);

        dogHeadChomp = new Animation(AnimConst.ANIM_FRAME_RATE,getFramesConsecutive(dogHeadFrames[0],0,5),
                Animation.PlayMode.LOOP
        );
        dogHeadIdle = new Animation(AnimConst.ANIM_FRAME_RATE,getFramesConsecutive(dogHeadFrames[0],0,1),
                Animation.PlayMode.LOOP
        );
        dogArm = cutLinear(atlas,"dog-arm",10,14);
        link = cut(atlas,"link",20,30);
        ball = cutSingle(atlas,"ball");
        dogTail = cutSingle(atlas,"dog-tail");
        //handAnimation = new Animation(0.16f, Array.with(hand), Animation.PlayMode.NORMAL);
    }



}
