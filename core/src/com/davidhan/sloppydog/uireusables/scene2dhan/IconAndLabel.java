package com.davidhan.sloppydog.uireusables.scene2dhan;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.davidhan.sloppydog.constants.Spacing;

/**
 * name: IconAndLabel
 * desc:
 * date: 2016-08-19
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class IconAndLabel extends Table {
    public IconAndLabel(Widget left, Widget right){
        this(left,right, Spacing.X_SMALL);

    }
    public IconAndLabel(Widget left, Widget right, float spacing){
        super();
        add(left).spaceRight(spacing);
        add(right);
        pack();
    }
}
