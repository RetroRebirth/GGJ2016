package com.teamname.goaton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Contact;

/**
 * Created by pya on 1/30/16.
 */
public abstract class Component implements Cloneable {
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


    protected void onCollisionEnter(Contact collision, GameObject other)
    {

    }
    protected  void onCollisionExit(Contact collision, GameObject other)
    {

    }
    public abstract String getID();

    protected void on(String msg, MsgHandler handler)
    {
        handler.setMsg(msg);
        gameObject.install(handler);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public GameObject gameObject;





}
