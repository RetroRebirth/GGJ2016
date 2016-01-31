package com.teamname.goaton.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
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
    protected void create() {
        if(!spawnAreas.isEmpty())
        {


            Rectangle chosen = spawnAreas.remove(0);

            float posX = chosen.getX() + (chosen.getWidth() / 2.0f);
            float posY = chosen.getY() + (chosen.getHeight() / 2.0f);

            Vector2 pos = new Vector2(posX, posY);
            gameObject.setPosition(pos);

            // Attach a demon spawn to this pit position
            GameObject go = new GameObject();
            go.addComponent(new DemonSpawnerComponent(pos));
            GameObject.Instantiate(go);
        }
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
