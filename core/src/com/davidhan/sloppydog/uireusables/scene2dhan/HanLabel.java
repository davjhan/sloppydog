package com.davidhan.sloppydog.uireusables.scene2dhan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.davidhan.sloppydog.app.IApp;

/**
 * name: HanLabel
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanLabel extends Label {
    boolean isForceAllCaps = true;
    public HanLabel(IApp iApp, CharSequence text) {
        super(text, iApp.res().skin);
        setText(getText().toString().toUpperCase());
    }

    public HanLabel(IApp iApp, CharSequence text, String styleName) {
        super(text, iApp.res().skin, styleName);
        setText(getText().toString().toUpperCase());
    }

    public HanLabel(IApp iApp, CharSequence text, String fontName, Color color) {
        super(text, iApp.res().skin, fontName, color);
        setText(getText().toString().toUpperCase());
    }

    public HanLabel(IApp iApp, CharSequence text, String fontName, String colorName) {
        super(text, iApp.res().skin, fontName, colorName);
        setText(getText().toString().toUpperCase());
    }

    public HanLabel(IApp iApp, CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public void forceAllCaps(boolean force){
        this.isForceAllCaps = force;
        checkTextCase();
    }

    @Override
    public void setText(CharSequence newText) {
        super.setText(newText);
    }
    private void checkTextCase(){
        if(isForceAllCaps){
            setText(getText().toString().toUpperCase());
        }
    }

    public void setFontColor(Color fontColor) {
        LabelStyle style = new LabelStyle(getStyle());
        style.fontColor = fontColor;
        setStyle(style);
    }

}
