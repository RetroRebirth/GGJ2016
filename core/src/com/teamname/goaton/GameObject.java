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
    public enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }
    private boolean created = false;
    public Direction direction = Direction.NONE;
    private Body body = null;
    private Vector2 position = new Vector2();
    public float radius = 1f;
    public int layer = 0;
    public HashMap<String, Component> components;
    public HashSet<String> tags = new HashSet<String>();
    private List<Component> renderOrderedComponents = new LinkedList<Component>();
    private Queue<Message> messages = new LinkedList<Message>();
    protected List<MsgHandler> handlers = new LinkedList<MsgHandler>();
    public List<GameObject> children = new ArrayList<GameObject>();
    public GameObject parent = null;


    public GameObject()
    {
        this.components = new HashMap<String, Component>();
    }





    public GameObject(GameObject other)
    {
        this.components = new HashMap<String, Component>(other.components);
        this.messages = new LinkedList<Message>();
        this.handlers = new LinkedList<MsgHandler>();
        this.tags = new HashSet<String>(other.tags);
        other.layer = layer;
        for(Map.Entry<String, Component> e : other.components.entrySet())
        {
            Component newComp = e.getValue().cloneComponent();

            addComponent(newComp);

        }
    }

    public static GameObject Instantiate(GameObject targ)
    {
        GameObject newGameObject = new GameObject(targ);
        GoatonWorld.addObject(newGameObject);

        return newGameObject;
    }

    public void addChild(GameObject child) throws RuntimeException
    {
        if (child.parent != null) {
            throw new RuntimeException("Child already is a child of an object");
        }
        this.children.add(child);
        child.parent = this;

    }
    public void removeChild(GameObject child) {
        if (children.contains(child)) {
            child.parent = null;
            this.children.remove(child);
        }
        else
        {
            System.err.println("Could not find child");
        }
    }

    public void addComponent(Component c)
    {
        c.provideGameObject(this);
        components.put(c.getID(),c);
        renderOrderedComponents.add(c);

    }
    public void destroy()
    {
        while(!messages.isEmpty())
        {
            Message m = messages.remove();
            for (GameObject child : children)
            {
                child.send(m);
            }
            for (MsgHandler h : handlers)
            {
                if(h.getMsg().equals(m.getMessage()))
                {
                    h.handle(m);
                }
            }
        }
    }
    public void send(Message msg)
    {
        messages.add(msg);
    }

    public void update(float dt)
    {



        for(Map.Entry<String, Component> e : components.entrySet())
        {
            e.getValue().update(dt);
        }
        while(!messages.isEmpty())
        {
            Message m = messages.remove();
            for (GameObject child : children)
            {
                child.send(m);
            }
            for (MsgHandler h : handlers)
            {
                if(h.getMsg().equals(m.getMessage()))
                {
                    h.handle(m);
                }
            }
        }


    }

    public void render(SpriteBatch sb)
    {
        for(Component e : renderOrderedComponents)
        {
            e.render(sb);
        }

    }

    public void create() {
        if (!created) {
            for (Map.Entry<String, Component> e : components.entrySet()) {
                e.getValue().create();
            }
            created = true;
        }
    }

    public void setPosition(Vector2 pos)
    {
        if(body != null)
        {
            body.setTransform(pos,body.getAngle());
        }
        else
        {
            this.position = new Vector2(pos);
        }
    }


    public void install(MsgHandler handler)
    {
        handlers.add(handler);
    }

    public Component getComponent(String componentName)
    {
        return components.get(componentName);
    }


    public void addPhysicsBody(Body body) {
        this.body = body;
        body.setTransform(this.position,0);
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

    public Vector2 getPosition() {
        Vector2 pOffset = new Vector2();
        if(parent != null)
        {
            pOffset = parent.getPosition();
        }
        if(body != null)
        {
            return new Vector2(body.getPosition()).add(pOffset);
        }
        else
        {
            return new Vector2(this.position).add(pOffset);
        }
    }

    public Vector2 getScreenPosition()
    {
        return getPosition().scl(GoatonWorld.TILE_SIZE);
    }
}
