package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.Prefabs.PitFactory;

/**
 * Created by pya on 1/31/16.
 */
public class PitSpawnerComponent extends SpawnerComponent {


    public PitSpawnerComponent() {
        super();
        go = PitFactory.Create();
    }

    @Override
    public Component cloneComponent() {
        return new PitSpawnerComponent();
    }

    @Override
    public String getID() {
        return "PitSpawnerComponent";
    }
}
