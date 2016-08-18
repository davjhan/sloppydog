package com.davidhan.sloppydog.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * name: TextureColorer
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TextureColorer {
    public static Pixmap atlasPixmap;
    public static Pixmap atlasPixmapTemp;
    public static TextureRegion tintTexture(TextureRegion textureRegion,Color tintColor){
        atlasPixmapTemp = getAtlasPixmap(textureRegion.getTexture());
        Pixmap pixmap = new Pixmap(textureRegion.getRegionWidth(),textureRegion.getRegionHeight(), Pixmap.Format.RGBA8888);
        for(int y = 0; y < pixmap.getHeight(); y ++){
            for(int x = 0; x < pixmap.getWidth(); x ++){
                if(atlasPixmapTemp.getPixel(textureRegion.getRegionX()+x,textureRegion.getRegionY()+y) != 0){
                    pixmap.drawPixel(x,y,Color.rgba8888(tintColor));
                }

            }
        }
        return new TextureRegion(new Texture(pixmap));
    }
    public static Pixmap getAtlasPixmap(Texture texture){
        if(atlasPixmap == null){
            if(!texture.getTextureData().isPrepared()){
                texture.getTextureData().prepare();
            }

            atlasPixmap = texture.getTextureData().consumePixmap();
            texture.getTextureData().disposePixmap();
        }
        return atlasPixmap;
    }
}
