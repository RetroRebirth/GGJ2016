package com.teamname.goaton.components;

import com.teamname.goaton.Component;

/**
 * Created by pya on 1/30/16.
 */
public class GoatPositionComponent extends PositionComponent {

    public GoatPositionComponent() {
        super();
    }

    @Override
    public Component cloneComponent() {
//        System.out.println(gameObject.getPosition().x);
//        System.out.println(gameObject.getPosition().y);
        return new GoatPositionComponent();
    }

    @Override
    public String getID() {
        return "GoatPositionComponent";
    }
}
