package com.davidhan.sloppydog.uireusables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTable;

/**
 * name: ListItem
 * desc:
 * date: 2016-06-30
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ListItem extends HanTable {
    protected IApp iApp;
    protected ClickListener onClickListener;
    HanLabel titleLabel;
    HanLabel valueLabel;
    String title;
    String value;
    NinePatchDrawable bgUp;
    NinePatchDrawable bgDown;
    boolean actionableValue;
    public ListItem(IApp iApp, ClickListener onClickListener, String title){
        this(iApp,onClickListener,title,"",false);
    }
    public ListItem(IApp iApp, ClickListener onClickListener, String title,String value,boolean actionableValue){
        super(iApp);
        this.iApp = iApp;
        this.onClickListener = onClickListener;
        this.title = title;
        this.value = value;
        this.actionableValue = actionableValue;
        build();
    }

    private void build() {
        setTouchable(Touchable.enabled);
        Color titleColor;
        bgUp = new NinePatchDrawable(iApp.res().textures.listItemUnderline[0]);
        bgDown = new NinePatchDrawable(iApp.res().textures.listItemUnderline[1]);
        background( bgUp);

        titleColor = Colors.get(com.davidhan.sloppydog.resources.ColorNames.TEXT_REG);
        if(onClickListener != null){
            //bg.setDisabled(true);
            addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    background(bgDown);
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    background(bgUp);
                    onClickListener.onClick();
                    super.touchUp(event, x, y, pointer, button);
                }
            });
            titleColor = Colors.get(com.davidhan.sloppydog.resources.ColorNames.TEXT_DISABLED);
        }
        valueLabel = new HanLabel(iApp,value);
        if(actionableValue) {
            valueLabel.setColor(Colors.get(com.davidhan.sloppydog.resources.ColorNames.TEXT_REG));
        }else{
            Colors.get(com.davidhan.sloppydog.resources.ColorNames.TEXT_DISABLED);
        }

        titleLabel = new HanLabel(iApp,title);
        titleLabel.setColor(titleColor);
        titleLabel.setWrap(true);
        titleLabel.setAlignment(Align.topLeft);
        pad(8);
        padTop(12);
        Cell cell = add(titleLabel).fillX().minWidth(140).expandX();
        if(!value.isEmpty()){
            cell.spaceRight(Spacing.REG);
            add(valueLabel).growX();
        }
        pack();

    }

    @Override
    public boolean remove() {
        iApp = null;
        onClickListener = null;
        return super.remove();
    }

    public void setText(String text) {
        this.titleLabel.setText(text);
    }
}
