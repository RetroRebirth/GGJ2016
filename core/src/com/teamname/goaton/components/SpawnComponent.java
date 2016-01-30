package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by kpidding on 1/30/16.
 */
public class SpawnComponent extends Component{
    GameObject toClone;
    public SpawnComponent(GameObject shoot)
    {
        toClone = shoot;
    }

    @Override
    protected void create() {
        final SpawnComponent thisCmp = this;
        on("shoot",new MsgHandler() {
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
