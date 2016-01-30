package com.teamname.goaton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

import java.util.*;

/**
 * Created by pya on 1/30/16.
 */
public class GameObject {
    private Body body = null;
    public Vector2 position = new Vector2();
    public float radius = 1f;

    public HashMap<String, Component> components;
    public List<String> tags;

    private Queue<Message> messages = new LinkedList<Message>();
    private List<MsgHandler> handlers = new LinkedList<MsgHandler>();



    public GameObject()
    {
        this.components = new HashMap<String, Component>();
    }

    public GameObject(GameObject other)
    {
        this.components = new HashMap<String, Component>(other.components);
        this.messages = new LinkedList<Message>();
        this.handlers = new LinkedList<MsgHandler>();
        other.tags = this.tags;
        for(Map.Entry<String, Component> e : other.components.entrySet())
        {
            try {
                Component newComp = (Component)e.getValue().clone();
                addComponent(newComp);
            }
            catch (CloneNotSupportedException exc)
            {
                System.err.println("Clone not supported for " + e.getValue().getClass().toString());
            }
        }
    }

    public static GameObject Instantiate(GameObject targ)
    {
        GameObject newGameObject = new GameObject(targ);

        GoatonWorld.addObject(newGameObject);

        return newGameObject;
    }


    public void addComponent(Component c)
    {
        c.provideGameObject(this);
        components.put(c.getID(),c);

    }

    public void send(Message msg)
    {
        messages.add(msg);
    }

    void update(float dt)
    {



        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().update(dt);
        }
        while(!messages.isEmpty())
        {
            Message m = messages.remove();
            for (MsgHandler h : handlers)
            {
                if(h.getMsg().equals(m.getMessage()))
                {
                    h.handle(m);
                }
            }
        }


    }

    void render(SpriteBatch sb)
    {
        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().render(sb);
        }

    }

    void create()
    {
        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().create();
        }
    }


    void install(MsgHandler handler)
    {
        handlers.add(handler);
    }

    public Component getComponent(String componentName)
    {
        return components.get(componentName);
    }


    public void addPhysicsBody(Body body) {
        this.body = body;
        this.body.setUserData(this);
    }

    public void onCollisionEnter(Contact c, GameObject other)
    {
        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().onCollisionEnter(c, other);
        }
    }
    public void onCollisionExit(Contact c, GameObject other)
    {
        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().onCollisionExit(c, other);
        }
    }

    public Body getBody() {
        return body;
    }
}
