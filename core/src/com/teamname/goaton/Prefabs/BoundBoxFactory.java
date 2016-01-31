package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.BoundBoxComponent;
import javafx.beans.property.MapProperty;

/**
 * Created by kpidding on 1/30/16.
 */
public class BoundBoxFactory {
    public static GameObject Create(MapObject boundBox, boolean blockThrows)
    {
        GameObject go = new GameObject();
        MapProperties prop = boundBox.getProperties();
        float x = (Float)prop.get("x");
        float y = (Float)prop.get("y");
        float wd = (Float) prop.get("width");
        float ht = (Float)prop.get("height");
        go.addComponent(new BoundBoxComponent(x,y,wd,ht,blockThrows));
        return go;
    }
}
