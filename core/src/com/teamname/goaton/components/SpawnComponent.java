package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.*;

import java.util.Random;

/**
 * Created by kpidding on 1/30/16.
 */
public class SpawnComponent extends Component {
    GameObject toClone;
    Random random = new Random();
    public SpawnComponent(GameObject spawn)
    {
        toClone = spawn;
    }

    @Override
    protected void create() {
        final SpawnComponent thisCmp = this;
        on("spawn", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                float boundedWidth = random.nextFloat() * GoatonWorld.worldWidth;
                float boundedHeight = random.nextFloat() * GoatonWorld.worldHeight;

                GameObject newObj = GameObject.Instantiate(thisCmp.toClone);
                newObj.position = new Vector2(boundedWidth, boundedHeight);
            }
        });

        on("spawnOrigin", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                GameObject newObj = GameObject.Instantiate(thisCmp.toClone);
                newObj.position = new Vector2(thisCmp.gameObject.position);
            }
        });
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SpawnComponent(toClone);
    }

    @Override
    public String getID() {
        return "SpawnComponent";
    }


}
