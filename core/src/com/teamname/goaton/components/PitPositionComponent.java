package com.teamname.goaton.components;

import com.teamname.goaton.Component;

/**
 * Created by pya on 1/30/16.
 */
public class PitPositionComponent extends PositionComponent {


    @Override
    public Component cloneComponent() {
        return new PitPositionComponent();
    }

    @Override
    public String getID() {
        return "PitPositionComponent";
    }

}
