package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by kpidding on 1/30/16.
 */
public class ShooterComponent extends Component{
    GameObject toClone;
    public ShooterComponent(GameObject shoot)
    {
        toClone = shoot;
    }

    @Override
    protected void create() {
        final ShooterComponent thisCmp = this;
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
        return new ShooterComponent(toClone);
    }

    @Override
    public String getID() {
        return "ShooterComponent";
    }
}
