package com.davidhan.lonelyball.factories;

import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.uireusables.scene2dhan.HanLabel;

/**
 * name: UIFactory
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class UIFactory {
    public static HanLabel getLabel(IApp iApp, String text, String fontName, String colorName){
        HanLabel hanLabel = new HanLabel(iApp,text,fontName,colorName);
      //  hanLabel.getGlyphLayout().
        return hanLabel;
    }
}
