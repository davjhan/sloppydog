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
    public Animation dogHeadDead;
    public TextureRegion[]dogLink;
    public TextureRegion[][] dogArm;
    public TextureRegion ball;
    public TextureRegion appleIcon;
    public TextureRegion[] arrow;
    public TextureRegion[] apple;
    public TextureRegion[] shadow;
    public TextureRegion[] dogTail;
    public TextureRegion[] grassTiles;
    public TextureRegion[] pauseButton;

    public TextureAssets(com.davidhan.sloppydog.resources.Assets assets){
        assets.load(PACKED_ATLAS, TextureAtlas.class);
    }

    public void prepare(com.davidhan.sloppydog.resources.Assets assets) {
        TextureAtlas atlas = assets.get(PACKED_ATLAS,TextureAtlas.class);
        bgNinePatches = cutNinesGroup(atlas,"bg-ninepatches",24,24,9);
        buttonNinePatches =cutNinesGroup(atlas,"btn-ninepatch",30,19,3,3,6);
        listItemUnderline = cutNinesGroup(atlas, "list-item-bg-nine", 12, 12, 3, 3, 3);


        dogHeadFrames = cut(atlas,"dog-head",60,40);

        dogHeadChomp = new Animation(AnimConst.ANIM_FRAME_RATE,getFramesConsecutive(dogHeadFrames[1],0,5),
                Animation.PlayMode.LOOP
        );
        dogHeadIdle = new Animation(AnimConst.ANIM_FRAME_RATE_SLOWER,getFramesConsecutive(dogHeadFrames[0],0,8),
                Animation.PlayMode.LOOP
        );
        dogHeadDead = new Animation(AnimConst.ANIM_FRAME_RATE,getFramesConsecutive(dogHeadFrames[2],0,1),
                Animation.PlayMode.NORMAL
        );
        dogArm = cut(atlas,"dog-arm",10,14);
        grassTiles = cutLinear(atlas,"grass-tiles",15,15);
        dogLink = cutLinear(atlas,"dog-link",20,30);
        arrow = cutLinear(atlas,"arrow",30,50);
        appleIcon = cutSingle(atlas,"apple-icon");
        ball = cutSingle(atlas,"ball");
        apple = cutLinear(atlas,"apple",40,40);
        shadow = cutLinear(atlas,"shadow",40,40);
        pauseButton = cutLinear(atlas,"pause-button",20,30);
        dogTail = cutLinear(atlas,"dog-tail",40,40);
        //handAnimation = new Animation(0.16f, Array.with(hand), Animation.PlayMode.NORMAL);
    }



}
