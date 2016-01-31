package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Scene;
import com.teamname.goaton.components.BridgeComponent;
import com.teamname.goaton.components.StaticSpriteRenderComponent;

/**
 * Created by kpidding on 1/31/16.
 */
public class BridgeTileFactory {
    public static void Create(Scene scn, Vector2 position, int numTiles)
    {
        Sprite bridgeSprite = new Sprite(new Texture(Gdx.files.internal(Assets.bridge)));
        bridgeSprite.scale(4.f);
        for(int i = 0; i < numTiles; i++)
        {
            GameObject go = new GameObject();
            go.addComponent(new BridgeComponent(new Sprite(bridgeSprite),new Vector2(position),0.5f*i));
            position.y += 4*bridgeSprite.getHeight()/GoatonWorld.TILE_SIZE;
            go.layer = Assets.PIT_LAYER;
            scn.addObject(go);
        }
    }


}
