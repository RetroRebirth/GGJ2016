package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by pya on 1/30/16.
 */
public class PositionComponent extends Component {
    protected float boundedWidth;
    protected float boundedHeight;

    public PositionComponent() {}

    @Override
    protected void create() {
        //final Component thisComp = this;

        boundedWidth =  GoatonWorld.Random.nextFloat() * GoatonWorld.worldWidth * 4;
        boundedHeight = GoatonWorld.Random.nextFloat() * GoatonWorld.worldHeight * 4;


        gameObject.setPosition(new Vector2(boundedWidth, boundedHeight));
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
