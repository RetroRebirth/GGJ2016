package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonBossAnimatorComponent extends Component {

    private Sprite head;
    private Sprite jaw;
    private Sprite body;
    private float scale = 5.5f;
    private float headBob = 20;
    private float jawBob = 10;
    private float bodyBob = 3;
    private  float t;
    private float damageTime = 0;
    public DemonBossAnimatorComponent()
    {
        head = new Sprite(new Texture(Gdx.files.internal("art/BossDemon/l1_demonKing_1.png")));
        jaw = new Sprite(new Texture(Gdx.files.internal("art/BossDemon/l2_demonKing_1.png")));
        body = new Sprite(new Texture(Gdx.files.internal("art/BossDemon/l0_demonKing_1.png")));
        head.scale(scale);
        jaw.scale(scale);
        body.scale(scale);

    }

    @Override
    protected void create() {
        this.on("damaged", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                damageTime = 0.25f;
                head.setColor(Color.BLUE);
                jaw.setColor(Color.BLUE);

            }
        });
    }

    @Override
    protected void update(float dt) {
        t += dt;
        float xOff = 0;
        if(damageTime > 0)
        {
            xOff = 4;
            damageTime -= dt;
            if(damageTime <= 0)
            {
                head.setColor(Color.WHITE);
                jaw.setColor((Color.WHITE));
            }
        }
        head.setPosition(gameObject.getScreenPosition().x + xOff*(float)Math.cos(30*t), gameObject.getScreenPosition().y + (float)Math.sin(t)*headBob);
        jaw.setPosition(gameObject.getScreenPosition().x+xOff*(float)Math.cos(30*t + Math.PI), gameObject.getScreenPosition().y + (float)(Math.sin(t)*headBob) + (float)Math.sin(t*1.05 + 0.5) * jawBob);
        body.setPosition(gameObject.getScreenPosition().x+xOff*(float)Math.cos(30*t), gameObject.getScreenPosition().y + (float)Math.sin(t + 0.25) * bodyBob);
    }

    @Override
    protected void render(SpriteBatch sb) {
        body.draw(sb);
        head.draw(sb);
        jaw.draw(sb);


    }

    public String getID() {
        return "DemonBossAnimatorComponent";
    }



    @Override
    public Component cloneComponent() {
        return new DemonBossAnimatorComponent();
    }
}
