package com.teamname.goaton.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.teamname.goaton.*;

/**
 * Created by kpidding on 1/31/16.
 */
public class PlayerTriggerComponent extends Component {
    private Message toSend;
    private boolean fireOnce;
    private boolean fired;
    private Rectangle rect;
    public void create()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(this.rect.getWidth(), this.rect.getHeight());


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = ObjectTypes.BOUNDARY;
        fixtureDef.filter.maskBits = ObjectTypes.PLAYER;

        fixtureDef.restitution = 0;
        this.gameObject.getBody().createFixture(fixtureDef);
        gameObject.setPosition(new Vector2(this.rect.x + this.rect.width , this.rect.y + this.rect.height));
        rect.dispose();

    }

    public PlayerTriggerComponent(float x, float y, float wd, float ht, Message msgToSend, boolean fireOnce) {
        rect = new Rectangle(x/ GoatonWorld.TILE_SIZE, y/GoatonWorld.TILE_SIZE,wd/GoatonWorld.TILE_SIZE,ht/GoatonWorld.TILE_SIZE);
        toSend = msgToSend;
        this.fireOnce = fireOnce;
    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        if(!fireOnce || !fired)
        {
            GoatonWorld.sendGlobalMessage(toSend);
            fired = true;
        }
    }

    @Override
    public String getID() {
        return "PlayerTriggerComponent";
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
