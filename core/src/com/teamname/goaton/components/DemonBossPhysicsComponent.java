package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.teamname.goaton.*;

/**
 * Created by pya on 1/30/16.
 */
public class DemonBossPhysicsComponent extends Component {

    public boolean isHitboxOn() {
        return hitboxOn;
    }

    boolean hitboxOn;
    Fixture fixture;
    short nobits = ObjectTypes.GOAT_AIR;
    short normalBits = ObjectTypes.PLAYER | ObjectTypes.GOAT_AIR  | ObjectTypes.DEMON | ObjectTypes.GOAT;
    FixtureDef fixtureDef;

    private final float fireRadiusY = 13.0f;
    private final float fireRadiusFan = 5.0f;

    private final float normalRadius = 0.75f;

    public void setMaskBits(boolean hitbox)
    {
        hitboxOn = hitbox;
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(new float[]{-4.0f,0.0f,-fireRadiusFan,-fireRadiusY,fireRadiusFan,-fireRadiusY,4.0f,0.0f});
        CircleShape circle = new CircleShape();
        circle.setRadius(hitbox ? fireRadiusY : normalRadius);
        fixtureDef.shape = circle;
        fixtureDef.filter.maskBits = hitbox ? normalBits : nobits;
        gameObject.getBody().destroyFixture(fixture);
        fixture = gameObject.getBody().createFixture(fixtureDef);
        fixtureDef.restitution = 1.0f;
        circle.dispose();
        polygonShape.dispose();
    }

    @Override
    protected void create()
    {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        CircleShape circle = new CircleShape();
        circle.setRadius(normalRadius);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.DEMON | ObjectTypes.PIT;
        fixtureDef.filter.maskBits = nobits;

        fixture = this.gameObject.getBody().createFixture(fixtureDef);

        circle.dispose();

        on("fireon", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                setMaskBits(true);
                            }
                        }, 500);

            }
        });

        on("fireoff", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                setMaskBits(false);
            }
        });
    }

    @Override
    public Component cloneComponent() {
        return new DemonBossPhysicsComponent();
    }

    @Override
    public String getID() {return "DemonBossPhysicsComponent";}
}
