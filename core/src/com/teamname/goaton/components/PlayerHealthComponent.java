package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Assets;
import com.teamname.goaton.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by christopherwilliams on 1/31/16.
 */
public class PlayerHealthComponent extends Component {
    public int health = 3;
    protected List<Sprite> sprites;

    @Override
    protected void create() {
        super.create();
        // Initialize health sprites
        sprites = new ArrayList<Sprite>();
        for (int i = 0; i < health; i++) {
            Sprite s = new Sprite(new Texture(Gdx.files.internal(Assets.player_head)));
            s.setOrigin(s.getWidth()/2,s.getHeight()/2);
//            s.setPosition(0, 0);

            sprites.add(s);
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        super.render(sb);
        // Render health icons
        for(Sprite s : sprites) {
            s.draw(sb);
        }
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
