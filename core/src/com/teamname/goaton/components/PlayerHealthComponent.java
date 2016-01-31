package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopherwilliams on 1/31/16.
 */
public class PlayerHealthComponent extends Component {

    private final float GUI_X_START = -304;
    private final float GUI_X_INCR = 48;
    private final float GUI_Y = 132;

    private final int MAX_HEALTH = 3;

    public int health = MAX_HEALTH;
    protected List<Sprite> sprites;

    @Override
    protected void create() {
        super.create();
        // Initialize health sprites
        sprites = new ArrayList<Sprite>();
        for (int i = 0; i < health; i++) {
            Sprite s = new Sprite(new Texture(Gdx.files.internal(Assets.heart)));
            s.setSize(32, 32);
            s.setOrigin(s.getWidth()/2,s.getHeight()/2);
            s.setPosition(GUI_X_START + i*GUI_X_INCR, GUI_Y);

            sprites.add(s);
        }

        this.on("player_hit",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                health--;
                if (health <= 0) {
                    GoatonWorld.sendGlobalMessage(new Message("player_dead"));
                }
                if (health >= 0) {
                    sprites.remove(sprites.size() - 1);
                }
            }
        });
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
