package com.teamname.goaton.components;

import com.teamname.goaton.Component;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatPropertyComponent extends Component{

    enum GoatColor
    {
        YELLOW,
        BLACK,
        BLUE,
        WHITE
    }
    public GoatColor color;
    GoatPropertyComponent(GoatColor c)
    {
        this.color = c;
    }
    @Override
    public String getID() {
        return "GoatPropertyComponent";
    }

    @Override
    public Component cloneComponent() {
        return new GoatPropertyComponent(color);
    }

}
