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
    private int spinLeftBtn;
    private int spinRightBtn;
    private boolean abtnToggle;
    private boolean lbtnToggle;
    private boolean rbtnToggle;


    public ControllerInputSorce(Controller ctrl)
    {
        this.ctrl = ctrl;
        if(Xbox.isXboxController(ctrl))
        {
            xAx = Xbox.L_STICK_HORIZONTAL_AXIS;
            yAx = Xbox.L_STICK_VERTICAL_AXIS;
            aBtn = Xbox.A;
            bBtn = Xbox.B;
            spinLeftBtn = Xbox.L_BUMPER;
            spinRightBtn = Xbox.R_BUMPER;
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
        if(abtnToggle)
        {
            if(!ctrl.getButton(aBtn))
            {
                abtnToggle = false;
            }

            return false;
        }
        else if(ctrl.getButton(aBtn))
        {
            abtnToggle = true;
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isDebugButtonPressed()
    {
       return false;
    }


    @Override
    public boolean isGlowButtonPressed() {
        return false;
    }

    @Override
    public boolean isSpinLeftButtonPressed() {
        if(lbtnToggle)
        {
            if(!ctrl.getButton(spinLeftBtn))
            {
                lbtnToggle = false;
            }

            return false;
        }
        else if(ctrl.getButton(spinLeftBtn))
        {
            lbtnToggle = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean isSpinRightButtonPressed() {
        if(rbtnToggle)
        {
            if(!ctrl.getButton(spinRightBtn))
            {
                rbtnToggle = false;
            }

            return false;
        }
        else if(ctrl.getButton(spinRightBtn))
        {
            rbtnToggle = true;
            return true;
        }
        else
        {
            return false;
        }
    }
}
