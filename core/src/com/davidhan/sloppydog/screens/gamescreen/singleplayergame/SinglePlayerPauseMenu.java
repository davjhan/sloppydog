package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.modals.ModalBase;
import com.davidhan.sloppydog.resources.HanSkin;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton;

/**
 * name: SinglePlayerPauseMenu
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerPauseMenu extends ModalBase {
    private PauseMenuListener pauseMenuListener;

    public SinglePlayerPauseMenu(IApp iApp, PauseMenuListener pauseMenuListener) {
        super(iApp);
        this.pauseMenuListener = pauseMenuListener;
        begin();
    }

    @Override
    protected void initContents() {
        HanLabel title = new HanLabel(iApp,"PAUSED", HanSkin.LabelStyles.TITLE);
        HanTextButton restart = new HanTextButton(iApp,"RESTART",HanSkin.DEFAULT);
        HanTextButton resume = new HanTextButton(iApp,"RESUME", HanSkin.ButtonStyles.PRIMARY);

        restart.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                pauseMenuListener.restartGame();
                close();
            }
        });
        resume.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                close();
            }
        });


        table.add(title).spaceBottom(Spacing.SPACE_MD);
        table.row();
        table.add(restart).fillX().spaceBottom(Spacing.SPACE_REG);
        table.row();
        table.add(resume).fillX();
    }

    @Override
    protected void onCloseComplete() {
        pauseMenuListener.resume();
    }



    public interface PauseMenuListener{
        void resume();
        void restartGame();
    }

    @Override
    public boolean remove() {
        pauseMenuListener = null;
        return super.remove();
    }
}
