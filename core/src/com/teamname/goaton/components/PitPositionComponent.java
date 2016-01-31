package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Prefabs.DemonSpawnerFactory;

/**
 * Created by pya on 1/30/16.
 */
public class PitPositionComponent extends PositionComponent {

    public PitPositionComponent() {
        super();
        spawnAreas = GoatonWorld.PitSpawnAreas;
    }

    @Override
    protected void create() {
        super.create();
        Vector2 pitPos = gameObject.getPosition();
        GameObject demonSpawner = DemonSpawnerFactory.Create(pitPos);
        GameObject.Instantiate(demonSpawner);
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
