package com.davidhan.armball.factories;

/**
 * name: UIFactory
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class UIFactory {
    public static com.davidhan.armball.uireusables.scene2dhan.HanLabel getLabel(com.davidhan.armball.app.IApp iApp, String text, String fontName, String colorName){
        com.davidhan.armball.uireusables.scene2dhan.HanLabel hanLabel = new com.davidhan.armball.uireusables.scene2dhan.HanLabel(iApp,text,fontName,colorName);
      //  hanLabel.getGlyphLayout().
        return hanLabel;
    }
}
