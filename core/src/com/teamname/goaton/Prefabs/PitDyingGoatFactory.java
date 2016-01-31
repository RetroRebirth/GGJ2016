package com.teamname.goaton.Prefabs;

import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.DyingGoatComponent;
import com.teamname.goaton.components.ParticleComponent;

/**
 * Created by pya on 1/31/16.
 */
public class PitDyingGoatFactory  {

    public static GameObject Create(GameObject goat) {
        GameObject go = new GameObject();
        go.addComponent(new DyingGoatComponent(goat.getComponent("GoatAnimatedSpriteComponent")));
        go.addComponent(new ParticleComponent("art/particleEffects/bloodsplatter.p","bloodout"));


        //go.addComponent();

        return go;
    }


}
