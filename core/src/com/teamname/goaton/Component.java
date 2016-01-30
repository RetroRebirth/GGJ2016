package com.teamname.goaton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pya on 1/30/16.
 */
public abstract class Component {
    public Component()
    {
        gameObject = null;
    }
    public void provideGameObject(GameObject other)
    {
        gameObject = other;
    }
    protected void create()
    {

    }
    protected void update(float dt)
    {

    }
    protected void render(SpriteBatch sb)
    {

    }
    protected void onHit(GameObject other)
    {

    }
    public abstract String getID();

    protected void on(String msg, MsgHandler handler)
    {
        handler.setMsg(msg);
        gameObject.install(handler);
    }

    public GameObject gameObject;





}
