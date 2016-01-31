package com.teamname.goaton.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Xbox;

/**
 * Created by kpidding on 1/30/16.
 */
public class ControllerInputSorce implements GameInputSource {
    private Controller ctrl;
    private int xAx;
    private int yAx;
    private  int aBtn;
    private int bBtn;
    private boolean btnToggle;

    public ControllerInputSorce(Controller ctrl)
    {
        this.ctrl = ctrl;
        if(Xbox.isXboxController(ctrl))
        {
            xAx = Xbox.L_STICK_HORIZONTAL_AXIS;
            yAx = Xbox.L_STICK_VERTICAL_AXIS;
            aBtn = Xbox.A;
            bBtn = Xbox.B;
        }
    }
    @Override
    public float getMovementOnAxis(Axis axis) {
        switch(axis)
        {

            case X_AXIS:
                float x = ctrl.getAxis(xAx);
                if (Math.abs(x) < 0.1)
                {
                    x = 0;
                }
                return x;

            case Y_AXIS:
                float y = ctrl.getAxis(yAx);
                if(Math.abs(y) < 0.1)
                {
                    y = 0;
                }
                return -y;
            default:
                throw new IllegalArgumentException();
        }
    }


    @Override
    public boolean isThrowButtonPressed() {
        if(btnToggle)
        {
            if(!ctrl.getButton(aBtn))
            {
                btnToggle = false;
            }

            return false;
        }
        else if(ctrl.getButton(aBtn))
        {
            btnToggle = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean isGlowButtonPressed() {
        if(btnToggle)
        {
            if(!ctrl.getButton(bBtn))
            {
                btnToggle = false;
            }

            return false;
        }
        else if(ctrl.getButton(bBtn))
        {
            btnToggle = true;
            return true;
        }
        else
        {
            return false;
        }
    }
}
