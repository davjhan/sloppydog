package com.davidhan.armball.resources;

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
                new Label.LabelStyle(getFont(com.davidhan.armball.resources.FontAssets.Font.SPORTY), Colors.get(ColorNames.NEAR_BLACK))
        );
    }

    private void initButtonStyles(Assets assets) {
        add(DEFAULT, makeButtonStyle(assets, 0, 1, com.davidhan.armball.resources.FontAssets.Font.SPORTY_BTN_REG));
        add(ButtonStyles.PRIMARY, makeButtonStyle(assets, 2, 3, com.davidhan.armball.resources.FontAssets.Font.SPORTY_BTN_PRIMARY));
        add(DISABLED, makeButtonStyle(assets, 4, 5, com.davidhan.armball.resources.FontAssets.Font.SPORTY_BTN_DISABLED));

    }

    private Object makeButtonStyle(Assets assets, int downIndex, int upIndex, String fontName) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(
                new NinePatchDrawable(assets.textures.buttonNinePatches[downIndex]),
                new NinePatchDrawable(assets.textures.buttonNinePatches[upIndex]),
                new NinePatchDrawable(assets.textures.buttonNinePatches[downIndex]),
                getFont(fontName)
        );
        style.checkedOffsetY = 1;
        style.pressedOffsetY = -1;
        style.unpressedOffsetY = 1;

        return style;
    }

    private void initFonts(Assets assets) {
        add(DEFAULT, assets.get(com.davidhan.armball.resources.FontAssets.Font.SGK, BitmapFont.class));
        for (Field f : ClassReflection.getFields(com.davidhan.armball.resources.FontAssets.Font.class)) {
            try {
                String value = (String) f.get(f);
                add(value, assets.get(value, BitmapFont.class));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
        getFont(com.davidhan.armball.resources.FontAssets.Font.SGK_PARAGRAPH).getData().setLineHeight(16);
    }
}
