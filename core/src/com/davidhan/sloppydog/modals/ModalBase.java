package com.davidhan.sloppydog.modals;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.AnimConst;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.constants.UIConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.uireusables.GameGroup;
import com.davidhan.sloppydog.uireusables.SolidDrawable;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTable;

/**
 * name: BaseModal
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class ModalBase extends GameGroup {
    protected Image dim;
    boolean cancelable;
    protected IApp iApp;
    protected Table table;
    boolean locked;

    public ModalBase(IApp iApp){
        this(iApp,true);
    }
    public ModalBase(IApp iApp, boolean cancelable) {
        this.iApp = iApp;
        this.cancelable = cancelable;

    }

    protected void begin(){
        makeDim();
        makeTable();
        spawn(dim);
        spawn(table, Display.HALF_WIDTH, Display.HALF_HEIGHT, Align.center);
        setTableEnterAction();
        setLocked(true);
    }
    protected void setTableFullWidth(Table table){
        table.setWidth(Display.WIDTH- UIConst.Modal.SIDE_PAD*2);
        table.setX(Display.WIDTH/2-table.getWidth()/2);
    }
    private void setTableEnterAction() {
        table.moveBy(0,-10);
        table.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.delay(AnimConst.DUR_TRANSITION),
                Actions.parallel(
                        Actions.moveBy(0, 10, AnimConst.DUR_TRANSITION,Interpolation.pow2In),
                        Actions.fadeIn(AnimConst.DUR_TRANSITION,Interpolation.pow2In))
                )
        );

    }
    public void setLocked(boolean locked) {
        this.locked = locked;
        table.setTouchable(locked ? Touchable.disabled:Touchable.enabled);
    }

    private void makeTable() {
        table = new HanTable(iApp);
        table.setBackground(new NinePatchDrawable(iApp.res().textures.bgNinePatches[0]));
        table.pad(Spacing.LARGE);
        initContents();
        table.pack();
        table.addListener(new ClickListener());
    }

    protected abstract void initContents();

    private void makeDim() {
        dim = new Image(new SolidDrawable(Display.WIDTH, Display.HEIGHT, Colors.get(ColorNames.MODAL_DIM)));
        dim.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.fadeIn(AnimConst.DUR_TRANSITION,Interpolation.pow2In)));
        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                setLocked(false);
            }
        }, AnimConst.DUR_TRANSITION*2);
        dim.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if(!cancelable) return;
                close();

            }
        });

    }
    protected void close() {
        if(locked) return;
        addAction(Actions.sequence(
                Actions.fadeOut(AnimConst.DUR_TRANSITION, Interpolation.pow2In),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        onCloseComplete();
                        remove();
                    }
                })));
        setLocked(true);

    }

    protected abstract void onCloseComplete();

    @Override
    public boolean remove() {
        iApp = null;
        return super.remove();
    }
}
