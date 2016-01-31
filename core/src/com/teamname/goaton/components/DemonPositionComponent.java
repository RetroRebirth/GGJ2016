package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by pya on 1/31/16.
 */
public class DemonPositionComponent extends PositionComponent {
    Vector2 pitPos;

    public DemonPositionComponent(Vector2 pitPos) {
        this.pitPos = pitPos;
    }

    @Override
    protected void create() {
//        System.out.println("pitPos: " + pitPos);
        gameObject.setPosition(pitPos);
    }

    @Override
    public Component cloneComponent() {
        return new DemonPositionComponent(pitPos);
    }

    @Override
    public String getID() {
        return "GoatPositionComponent";
    }
}
