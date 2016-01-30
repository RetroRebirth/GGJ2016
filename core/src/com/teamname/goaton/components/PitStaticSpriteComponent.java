package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PitStaticSpriteComponent extends StaticSpriteRenderComponent {
    public PitStaticSpriteComponent(Sprite sprite) {
        super(sprite);
    }

    @Override
    protected void create() {
        sprite.setColor(Color.RED);
        super.create();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PitStaticSpriteComponent psp = new PitStaticSpriteComponent(new Sprite(sprite));
        return psp;
    }
}