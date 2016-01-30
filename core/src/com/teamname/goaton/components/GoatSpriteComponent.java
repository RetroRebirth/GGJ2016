package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.World;

import java.util.Random;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatSpriteComponent extends SpriteRenderComponent {
    private static final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GOLD};
    public GoatSpriteComponent(Sprite sprite) {
        super(sprite);
    }

    @Override
    protected void create() {

        sprite.setColor(colors[World.Random.nextInt(colors.length)]);
        super.create();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GoatSpriteComponent gsp = new GoatSpriteComponent(new Sprite(sprite));
        return gsp;
    }
}
