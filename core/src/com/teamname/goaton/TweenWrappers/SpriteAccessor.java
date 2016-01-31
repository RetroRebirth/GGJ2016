package com.teamname.goaton.TweenWrappers;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by kpidding on 1/29/16.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int TWEEN_X = 0;
    public static final int TWEEN_Y = 1;
    public static final int TWEEN_XY = 2;
    public static final int TWEEN_ROT = 3;
    public static final int TWEEN_XYROT = 4;
    public static final int TWEEN_SCALEX = 5;
    public static final int TWEEN_SCALEY = 6;
    public static final int TWEEN_SCALEXY = 7;
    public static final int TWEEN_XYSCALEXY = 8;
    public static final int TWEEN_SCALEXYROT = 9;
    public static final int TWEEN_XYSCALEXYROT = 10;
    public static final int TWEEN_RGB = 11;
    public static final int TWEEN_ALPHA = 12;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case TWEEN_X:
                returnValues[0] = target.getX();
                return 1;
            case TWEEN_Y:
                returnValues[0] = target.getY();
                return 1;
            case TWEEN_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case TWEEN_ROT:
                returnValues[0] = target.getRotation();
                return 1;
            case TWEEN_XYROT:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getRotation();
                return 3;
            case TWEEN_SCALEX:
                returnValues[0] = target.getScaleX();
                return 1;
            case TWEEN_SCALEY:
                returnValues[0] = target.getScaleY();
                return 1;
            case TWEEN_SCALEXY:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            case TWEEN_XYSCALEXY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getScaleX();
                return 3;
            case TWEEN_SCALEXYROT:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                returnValues[2] = target.getRotation();
                return 3;
            case TWEEN_XYSCALEXYROT:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getScaleX();
                returnValues[3] = target.getScaleY();
                returnValues[4] = target.getRotation();
                return 5;
            case TWEEN_RGB:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
                return 3;
            case TWEEN_ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                assert false;
                return 0;
        }
    }


    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case TWEEN_X:
                target.setX(newValues[0]);
                break;
            case TWEEN_Y:
                target.setY(newValues[1]);
                break;
            case TWEEN_XY:
                target.setPosition(newValues[0], newValues[1]);
                break;
            case TWEEN_ROT:
                target.setRotation(newValues[0]);
                break;
            case TWEEN_XYROT:
                target.setPosition(newValues[0],newValues[1]);
                target.setRotation(newValues[2]);
                break;
            case TWEEN_SCALEX:
                target.setScale(newValues[0], target.getScaleY());
                break;
            case TWEEN_SCALEY:
                target.setScale(target.getScaleX(), newValues[0]);
                break;
            case TWEEN_SCALEXY:
                target.setScale(newValues[0], newValues[1]);
                break;
            case TWEEN_XYSCALEXY:
                target.setScale(newValues[2]);
                target.setPosition(newValues[0],newValues[1]);
                break;
            case TWEEN_SCALEXYROT:
                target.setScale(newValues[0], newValues[1]);
                target.setRotation(newValues[2]);
                break;
            case TWEEN_XYSCALEXYROT:
                target.setPosition(newValues[0], newValues[1]);
                target.setScale(newValues[2], newValues[3]);
                target.setRotation(newValues[4]);
                break;
            case TWEEN_RGB:
                target.setColor(new Color(newValues[0],newValues[1],newValues[2],target.getColor().a));
                break;
            case TWEEN_ALPHA:
                target.setAlpha(newValues[0]);
                break;
            default:
                assert false;
        }
    }
}
