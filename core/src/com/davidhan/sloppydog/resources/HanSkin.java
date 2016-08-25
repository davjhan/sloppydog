package com.davidhan.sloppydog.resources;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

/**
 * name: HanSkin
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanSkin extends Skin {
    public static final String DEFAULT = "default";
    public static final String DISABLED = "disabled";

    public class ButtonStyles {
        public static final String PRIMARY = "primary";
    }

    public class LabelStyles {
        public static final String TITLE = "title";
        public static final String BIG = "big";
        public static final String EXTRABIG = "extra-big";
    }

    public HanSkin(Assets assets) {
        initFonts(assets);
        initLabelStyles(assets);
        initButtonStyles(assets);

    }

    private void initLabelStyles(Assets assets) {
        add(DEFAULT,
                new Label.LabelStyle(getFont(DEFAULT), Colors.get(ColorNames.NEAR_BLACK))
        );
        add(LabelStyles.TITLE,
                new Label.LabelStyle(getFont(FontAssets.Font.SPORTY), Colors.get(ColorNames.NEAR_BLACK))
        );
        add(LabelStyles.BIG,
                new Label.LabelStyle(getFont(FontAssets.Font.SPORTY_32), Colors.get(ColorNames.NEAR_BLACK))
        );
        add(LabelStyles.EXTRABIG,
                new Label.LabelStyle(getFont(FontAssets.Font.SPORTY_48), Colors.get(ColorNames.NEAR_BLACK))
        );
    }

    private void initButtonStyles(Assets assets) {
        add(DEFAULT, makeButtonStyle(assets, 0, 1, FontAssets.Font.SPORTY,ColorNames.BTN_REGULAR_TEXT));
        add(ButtonStyles.PRIMARY, makeButtonStyle(assets, 2, 3, FontAssets.Font.SPORTY,ColorNames.OFF_WHITE));
        add(DISABLED, makeButtonStyle(assets, 4, 5, FontAssets.Font.SPORTY,ColorNames.BTN_DISABLED_TEXT));

    }

    private Object makeButtonStyle(Assets assets, int downIndex, int upIndex, String fontName,String colorName) {

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                new NinePatchDrawable(assets.textures.buttonNinePatches[downIndex]),
                new NinePatchDrawable(assets.textures.buttonNinePatches[upIndex]),
                new NinePatchDrawable(assets.textures.buttonNinePatches[downIndex]),
                getFont(fontName)
        );
        style.fontColor = Colors.get(colorName);
        style.checkedOffsetY = 0;
        style.pressedOffsetY = -2;
        style.unpressedOffsetY = 0;

        return style;
    }

    private void initFonts(Assets assets) {
        add(DEFAULT, assets.get(FontAssets.Font.SGK, BitmapFont.class));
        for (Field f : ClassReflection.getFields(FontAssets.Font.class)) {
            try {
                String value = (String) f.get(f);
                add(value, assets.get(value, BitmapFont.class));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
        getFont(FontAssets.Font.SPORTY_32).getData().markupEnabled = true;
        getFont(FontAssets.Font.SPORTY_48).getData().markupEnabled = true;
        //getFont(FontAssets.Font.SPORTY).getData().capHeight = 4;
        getFont(FontAssets.Font.SPORTY).getData().ascent = 3;
        getFont(FontAssets.Font.SPORTY).getData().descent = 0;

        getFont(FontAssets.Font.SPORTY_32).getData().ascent = 5;
        getFont(FontAssets.Font.SPORTY_32).getData().descent = 0;

        getFont(FontAssets.Font.SPORTY_48).getData().ascent = 7;
        getFont(FontAssets.Font.SPORTY_48).getData().descent = 0;
        getFont(FontAssets.Font.SGK_PARAGRAPH).getData().setLineHeight(16);
    }
}
