package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

import java.util.Random;

/**
 * Created by pya on 1/30/16.
 */
public class PositionComponent extends Component {
    protected float boundedWidth;
    protected float boundedHeight;



    public PositionComponent() {}

    @Override
    protected void create() {
        boundedWidth = GoatonWorld.Random.nextFloat() * GoatonWorld.worldWidth;
        boundedHeight = GoatonWorld.Random.nextFloat() * GoatonWorld.worldHeight;
        gameObject.getBody().setTransform(new Vector2(boundedWidth, boundedHeight), 0);

    }

    @Override
    public Component cloneComponent() {
        return new PositionComponent();
    }

    @Override
    public String getID() {
        return "PositionComponent";
    }
}
