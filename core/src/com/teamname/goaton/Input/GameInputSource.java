package com.teamname.goaton.Input;

/**
 * Created by kpidding on 1/30/16.
 */
public interface GameInputSource {
    enum Axis
    {
        X_AXIS,
        Y_AXIS
    }
    float getMovementOnAxis(Axis axis);
    boolean  isThrowButtonPressed();
    boolean isDebugButtonPressed();


}
