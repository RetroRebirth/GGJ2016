package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by pya on 1/30/16.
 */
public class GoatPositionComponent extends PositionComponent {

    public GoatPositionComponent() {
        super();
        spawnAreas = GoatonWorld.GoatSpawnAreas;
    }

    @Override
    public Component cloneComponent() {
        return new GoatPositionComponent();
    }

    @Override
    public String getID() {
        return "GoatPositionComponent";
    }
}
