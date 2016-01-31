package com.teamname.goaton.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by kpidding on 1/30/16.
 */
public class KeyboardInputSource implements GameInputSource {
    @Override
    public float getMovementOnAxis(Axis axis) {
        switch (axis)
        {
            case X_AXIS:
            {
                return  (Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -1.0f : 0.0f) +
                        (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ? 1.0f : 0.0f);

            }
            case Y_AXIS:
            {
                return  (Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -1.0f : 0.0f) +
                        (Gdx.input.isKeyPressed(Input.Keys.UP) ? 1.0f : 0.0f);

            }
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isThrowButtonPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    @Override
    public boolean isGlowButtonPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.B);
    }

    @Override
    public boolean isDebugButtonPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.Q);
    }

    @Override
    public boolean isSpinLeftButtonPressed() { return Gdx.input.isKeyJustPressed(Input.Keys.A);}

    @Override
    public boolean isSpinRightButtonPressed() { return Gdx.input.isKeyJustPressed(Input.Keys.D);}

    @Override
    public boolean isRestartButtonPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.R);
    }
}
