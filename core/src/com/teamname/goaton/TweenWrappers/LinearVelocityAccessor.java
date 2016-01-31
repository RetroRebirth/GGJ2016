package com.teamname.goaton.TweenWrappers;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by pya on 1/31/16.
 */
public class LinearVelocityAccessor implements TweenAccessor<Body> {
    public static final int TWEEN_X = 0;
    public static final int TWEEN_Y = 1;
    public static final int TWEEN_XY = 2;

    @Override
    public int getValues(Body target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case TWEEN_X:
                returnValues[0] = target.getLinearVelocity().x;
                return 1;
            case TWEEN_Y:
                returnValues[0] = target.getLinearVelocity().y;
                return 1;
            case TWEEN_XY:
                returnValues[0] = target.getLinearVelocity().x;
                returnValues[1] = target.getLinearVelocity().y;
                return 2;
            default:
                assert false;
                return 0;
        }
    }
    @Override
    public void setValues(Body target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case TWEEN_X:
                float curY = target.getLinearVelocity().y;
                target.setLinearVelocity(newValues[0], curY);
                break;
            case TWEEN_Y:
                float curX = target.getLinearVelocity().x;
                target.setLinearVelocity(curX, newValues[0]);
                break;
            case TWEEN_XY:
                target.setLinearVelocity(newValues[0], newValues[1]);
                break;
            default:
                assert false;
        }
    }
}
