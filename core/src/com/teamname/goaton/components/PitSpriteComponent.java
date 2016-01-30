package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.components.SpriteRenderComponent;

public class PitSpriteComponent extends SpriteRenderComponent {
    public PitSpriteComponent(Sprite sprite) {
        super(sprite);
    }

    @Override
    protected void create() {
        sprite.setColor(Color.RED);
        super.create();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PitSpriteComponent psp = new PitSpriteComponent(new Sprite(sprite));
        return psp;
    }
}