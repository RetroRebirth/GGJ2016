package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;

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
    protected void update(float dt) {
        t += dt;
        head.setPosition(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y + (float)Math.sin(t)*headBob);
        jaw.setPosition(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y + (float)(Math.sin(t)*headBob) + (float)Math.sin(t*1.05 + 0.5) * jawBob);
        body.setPosition(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y + (float)Math.sin(t + 0.25) * bodyBob);
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
