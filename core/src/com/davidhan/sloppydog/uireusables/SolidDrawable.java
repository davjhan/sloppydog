package com.davidhan.sloppydog.uireusables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * name: SolidDrawable
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SolidDrawable extends TextureRegionDrawable{
    public SolidDrawable(float width, float height, Color color){
        super(new TextureRegion(createTexture(width,height,color)));
    }
    public static Texture createTexture(float width, float height, Color color) {
        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, (int)width, (int)height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}
