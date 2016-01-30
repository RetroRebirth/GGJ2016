package com.teamname.goaton;

import com.teamname.goaton.Component;

import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public abstract class GameObject {
    public HashMap<Integer, Component> components;

    public GameObject(HashMap<Integer, Component> components) {
        this.components = new HashMap<Integer, Component>(components);
    }
}
