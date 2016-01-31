package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;

/**
 * Created by christopherwilliams on 1/31/16.
 */
public class PlayerHealthComponent extends Component {
    public int health = 3;

    @Override
    protected void create() {
        super.create();
        // TODO something...?
    }

    @Override
    protected void render(SpriteBatch sb) {
        super.render(sb);
        // TODO render 3 head icons
    }

    @Override
    public String getID() {
        return "PlayerHealthComponent";
    }

    @Override
    public Component cloneComponent() {
        return new PlayerHealthComponent();
    }
}
