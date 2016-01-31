package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by kpidding on 1/29/16.
 */
public class ScoreComponent extends Component {

    public ScoreComponent()
    {
    }

    @Override
    protected void create() {
        final Component comp = this;
        this.on("addScore", new MsgHandler() {
            @Override
            public void handle(Message msg) {
//                System.out.println("Added " + msg.getArg().toString() + "From " + comp.toString());
            }
        });
    }

    @Override
    public Component cloneComponent() {
        return new ScoreComponent();
    }

    @Override
    public String getID() {
        return "ScoreComponent";
    }


}
