package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Contact;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by lejonmcgowan on 1/31/16.
 */
public class DemonBossAttackComponent extends Component
{
    boolean appeared;
    float attackTimeElapsedSeconds;
    float attackRate = 10.0f;

    public DemonBossAttackComponent()
    {
        super();
    }

    @Override
    protected void create()
    {
        super.create();

        on("spawnBoss", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                appeared = true;
            }
        });
    }

    @Override
    protected void update(float dt)
    {
        super.update(dt);

        if(appeared) {
            attackTimeElapsedSeconds += Gdx.graphics.getDeltaTime();
            if (attackTimeElapsedSeconds > attackRate) {
                gameObject.send(new Message("attackAnim",4.0f));
                attackTimeElapsedSeconds = 0;
            }
        }
    }

    @Override
    protected void render(SpriteBatch sb)
    {
        super.render(sb);

    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other)
    {
        super.onCollisionEnter(collision, other);
    }

    @Override
    protected void onCollisionExit(Contact collision, GameObject other)
    {
        super.onCollisionExit(collision, other);
    }

    @Override
    public String getID()
    {
        return null;
    }

    @Override
    protected void on(String msg, MsgHandler handler)
    {
        super.on(msg, handler);
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
