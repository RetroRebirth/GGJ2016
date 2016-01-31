package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by pya on 1/30/16.
 */
public class PitPositionComponent extends PositionComponent {

    public PitPositionComponent() {
        super();
        spawnAreas = GoatonWorld.PitSpawnAreas;
    }

    @Override
    public Component cloneComponent() {
        /*System.out.println(gameObject.getPosition().x);
        System.out.println(gameObject.getPosition().y);*/
        return new PitPositionComponent();
    }

    @Override
    public String getID() {
        return "PitPositionComponent";
    }

}
