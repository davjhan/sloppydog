package com.davidhan.sloppydog.uireusables.scene2dhan;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.davidhan.sloppydog.app.IApp;

/**
 * name: HanImageButton
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanImageButton extends ImageButton {
    IApp iApp;
    public HanImageButton(IApp iApp) {
        super(iApp.res().skin);
        init(iApp);
    }

    private void init(IApp iApp) {
        this.iApp = iApp;
    }

    public HanImageButton(IApp iApp, String styleName) {
        super(iApp.res().skin, styleName);
        init(iApp);
    }

    public HanImageButton(IApp iApp, ImageButtonStyle style) {
        super(style);
        init(iApp);
    }

    public HanImageButton(IApp iApp, Drawable imageUp) {
        super(imageUp);
        init(iApp);
    }

    public HanImageButton(IApp iApp, Drawable imageUp, Drawable imageDown) {
        super(imageUp, imageDown);
        init(iApp);
    }

    public HanImageButton(IApp iApp, Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
        init(iApp);
    }
    public void setClickListener(final ClickListener clickListener){
        addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if(!isDisabled()) {
                    clickListener.onClick();
                }
            }
        });
    }


    @Override
    public boolean remove() {
        iApp = null;
        return super.remove();
    }
}
