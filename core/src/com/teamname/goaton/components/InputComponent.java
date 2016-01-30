package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.World;

/**
 * Created by kpidding on 1/29/16.
 */
public class InputComponent extends Component {
    public InputComponent()
    {

    }

    @Override
    protected void update(float dt) {
        super.update(dt);
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            World.sendGlobalMessage(new Message("addScore",10));
        }
    }

    @Override
    public String getID() {
        return "InputComponent";
    }
}
