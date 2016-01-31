package com.teamname.goaton.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.ObjectTypes;

/**
 * Created by kpidding on 1/30/16.
 */
public class BoundBoxComponent extends Component {
    Rectangle rect;
    boolean blockThrows;
    @Override

    public String getID() {
        return "BoundBoxComponent";
    }

    @Override
    public Component cloneComponent() {
        return null;
    }

    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(this.rect.getWidth(), this.rect.getHeight());


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.filter.categoryBits = ObjectTypes.BOUNDARY;
        if(blockThrows)
        {
            fixtureDef.filter.maskBits =
                    ObjectTypes.GOAT |
                    ObjectTypes.GOAT_AIR|
                            ObjectTypes.PIT |
                            ObjectTypes.DEMON ;//|
//                            ObjectTypes.PLAYER;

        }
        else
        {


        fixtureDef.filter.maskBits =
                ObjectTypes.GOAT |
                ObjectTypes.PIT |
                ObjectTypes.DEMON ;//|
//                ObjectTypes.PLAYER;
        }
            fixtureDef.restitution = 0;
        this.gameObject.getBody().createFixture(fixtureDef);
        gameObject.setPosition(new Vector2(this.rect.x + this.rect.width/2, this.rect.y + this.rect.height/2));
        rect.dispose();

    }

    public BoundBoxComponent(float x, float y, float wd, float ht, boolean blockThrows) {
        rect = new Rectangle(x/ GoatonWorld.TILE_SIZE, y/GoatonWorld.TILE_SIZE,wd/GoatonWorld.TILE_SIZE,ht/GoatonWorld.TILE_SIZE);
        this.blockThrows = blockThrows;
    }
}
