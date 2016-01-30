package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Component;

public class PitStaticSpriteComponent extends StaticSpriteRenderComponent {
    public PitStaticSpriteComponent(Sprite sprite) {
        super(sprite);
    }

    @Override
    protected void create() {
        super.create();
    }

    @Override
    public Component cloneComponent() {
        return new PitStaticSpriteComponent(new Sprite(sprite));
    }


}