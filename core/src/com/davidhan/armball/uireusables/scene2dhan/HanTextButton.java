package com.davidhan.armball.uireusables.scene2dhan;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * name: HanButton
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanTextButton extends TextButton {
    TextButtonStyle normalStyle;
    TextButtonStyle disabledStyle;
    public HanTextButton(com.davidhan.armball.app.IApp iApp, String text) {
        super(text, iApp.res().skin);
        init(iApp);
    }

    public HanTextButton(com.davidhan.armball.app.IApp iApp, String text, String styleName) {
        super(text, iApp.res().skin, styleName);
        init(iApp);
    }

    public HanTextButton(com.davidhan.armball.app.IApp iApp, String text, TextButtonStyle style) {
        super(text, style);
        init(iApp);
    }

    private void init(com.davidhan.armball.app.IApp iApp) {
        normalStyle = new TextButtonStyle(getStyle());
        disabledStyle = iApp.res().skin.get(com.davidhan.armball.resources.HanSkin.DISABLED,TextButtonStyle.class);
        getLabelCell().padTop(4);
        getLabelCell().padBottom(4);
        getLabelCell().padLeft(8);
        getLabelCell().padRight(8);
        pack();
    }

    @Override
    public void setDisabled(boolean isDisabled) {
        super.setDisabled(isDisabled);
    }

    public void setClickEnabled(boolean clickable){
        if(!clickable){
            setTouchable(Touchable.disabled);
            setStyle(disabledStyle);
        }else{
            setTouchable(Touchable.enabled);
            setStyle(normalStyle);
        }
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
    public void setText(String text) {
        super.setText(text);
    }
}
