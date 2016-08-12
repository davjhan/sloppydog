package com.davidhan.armball.modals;

import com.badlogic.gdx.Gdx;
import com.davidhan.armball.constants.Spacing;
import com.davidhan.armball.uireusables.scene2dhan.ClickListener;
import com.davidhan.armball.uireusables.scene2dhan.HanLabel;

/**
 * name: SettingsModal
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SettingsModal extends ModalBase {
    public SettingsModal(com.davidhan.armball.app.IApp iApp) {
        super(iApp);
    }

    @Override
    protected void initContents() {
        HanLabel title = new HanLabel(iApp,"Settings", com.davidhan.armball.resources.HanSkin.LabelStyles.TITLE);

        com.davidhan.armball.uireusables.ListItem versionLabel = new com.davidhan.armball.uireusables.ListItem(iApp,null,"version ","0.1",false);
        com.davidhan.armball.uireusables.ListItem resetLabel = new com.davidhan.armball.uireusables.ListItem(iApp, new ClickListener() {
            @Override
            public void onClick() {
                Gdx.app.log("tttt SettingsModal", "reset game");
            }
        }, "Reset Game");
        table.add(title).spaceBottom(Spacing.SPACE_MD);
        table.row();
        table.add(versionLabel).fillX();
        table.row();
        table.add(resetLabel).fillX();
    }
}
