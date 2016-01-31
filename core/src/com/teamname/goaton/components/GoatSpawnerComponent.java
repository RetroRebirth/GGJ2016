package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.Prefabs.GoatFactory;

/**
 * Created by pya on 1/31/16.
 */
public class GoatSpawnerComponent extends SpawnerComponent {

    public GoatSpawnerComponent() {
        super();
        go = GoatFactory.Create();
    }

    @Override
    public Component cloneComponent() {
        return new GoatSpawnerComponent();
    }

    @Override
    public String getID() {
        return "GoatSpawnerComponent";
    }
}
