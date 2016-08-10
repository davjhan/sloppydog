package com.davidhan.lonelyball.resources;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * name: FontAssets
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class FontAssets {
    public static final String FONT_SGK_100 = "font/src/SGK100.ttf";
    public static final String FONT_PM8 = "font/src/pixelmix.ttf";
    public static final String FONT_DAVID_PIX = "font/src/davidpix8.ttf";
    public static final String FONT_SPORTY = "font/src/Born2bSportyV2.ttf";
    public static final String FONT_QUADRIT = "font/src/Quadrit.ttf";

    public class Font {

        public static final String SGK = "sgk-16.ttf";
        public static final String SPORTY = "sporty-16.ttf";
        public static final String SPORTY_BTN_REG = "sporty-btn-reg.ttf";
        public static final String SPORTY_BTN_PRIMARY = "sporty-btn-primary.ttf";
        public static final String SPORTY_BTN_DISABLED = "sporty-btn-disabled.ttf";
        public static final String SGK_PARAGRAPH = "pixmix-8-prgrph.ttf";
    }

    public FontAssets(Assets assets) {
        assets.load(Font.SGK, BitmapFont.class, getLoaderParams(FONT_SGK_100, 16));
        assets.load(Font.SGK_PARAGRAPH, BitmapFont.class, getLoaderParams(FONT_SGK_100, 16));
        assets.load(Font.SPORTY, BitmapFont.class, getLoaderParams(FONT_SPORTY, 16));
        assets.load(Font.SPORTY_BTN_REG, BitmapFont.class,
                highlight(
                        getLoaderParams(FONT_SPORTY, 16),
                        Colors.get(ColorNames.BTN_REGULAR_TEXT),
                        Colors.get(ColorNames.BTN_REGULAR_HIGHLIGHT)
                )
        );
        assets.load(Font.SPORTY_BTN_PRIMARY, BitmapFont.class,
                highlight(
                        getLoaderParams(FONT_SPORTY, 16),
                        Colors.get(ColorNames.BTN_PRIMARY_TEXT),
                        Colors.get(ColorNames.BTN_PRIMARY_HIGHLIGHT)
                )
        );
        assets.load(Font.SPORTY_BTN_DISABLED, BitmapFont.class,
                highlight(
                        getLoaderParams(FONT_SPORTY, 16),
                        Colors.get(ColorNames.BTN_DISABLED_TEXT),
                        Colors.get(ColorNames.BTN_DISABLED_HIGHLIGHT)
                )
        );
        assets.load(Font.SPORTY, BitmapFont.class, getLoaderParams(FONT_SPORTY, 16));
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter getLoaderParams(String filename, int size) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = filename;
        params.fontParameters.size = size;
        params.fontParameters.hinting = FreeTypeFontGenerator.Hinting.Full;
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter highlight(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, Color textColor, Color highlightColor) {
        params.fontParameters.shadowOffsetY = -1;
        params.fontParameters.shadowColor = highlightColor;
        params.fontParameters.color = textColor;
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter shadow(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, Color textColor, Color highlightColor, int length) {
        params.fontParameters.shadowOffsetY = length;
        params.fontParameters.shadowColor = highlightColor;
        params.fontParameters.color = textColor;
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter space(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, int space) {
        params.fontParameters.spaceX = space;
        return params;
    }
}
