package com.teamname.goaton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import com.teamname.goaton.Component;

import java.util.*;
import java.util.logging.Handler;

/**
 * Created by pya on 1/30/16.
 */
public class GameObject {
    public Vector2 position = new Vector2();

    public HashMap<String, Component> components;


    private Queue<Message> messages = new LinkedList<Message>();
    private List<MsgHandler> handlers = new LinkedList<MsgHandler>();

    public GameObject() {
        this.components = new HashMap<String, Component>();
    }
    public GameObject(GameObject other)
    {
        this.components = new HashMap<String, Component>(other.components);
        this.messages = new LinkedList<Message>();
        this.handlers = new LinkedList<MsgHandler>();
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
        World.addObject(newGameObject);
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



}
