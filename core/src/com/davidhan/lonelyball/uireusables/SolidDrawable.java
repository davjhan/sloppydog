package com.davidhan.lonelyball.uireusables;

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
    public SolidDrawable(int width, int height, Color color){
        super(new TextureRegion(createTexture(width,height,color)));
    }
    public static Texture createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}
