package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by Simon on 1/30/2016.
 */
public class GoatPickupReceiver extends Component {
    protected void create() {
        on("pickup",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                //set state to picked up, adjust body
            }
        });
    }
    public String getID() {
        return "GoatPickupReceiver";
    }
}
