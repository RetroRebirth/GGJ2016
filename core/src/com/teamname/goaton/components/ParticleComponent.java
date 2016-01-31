package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.teamname.goaton.Component;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by lejonmcgowan runEffect 1/30/16.
 */
public class ParticleComponent extends Component
{

    private ParticleEffect particleEffect;
    private boolean runEffect;

    private boolean repeat;
    private String effectName;

    private String filePath;

    @Override
    protected void create()
    {
        on("glow", new MsgHandler()
        {
            @Override
            public void handle(Message msg) {
                if (gameObject.tags.contains("player")) {
                    if (!runEffect) {
                        setRepeat(true);
                        setRunEffect(true);
                    } else {
                        setRepeat(false);
                        setRunEffect(false);
                    }

                }
            }
        });
    }

    public ParticleComponent(String filepath, String effectName)
    {
        this.filePath = filepath;
        this.effectName = effectName;

        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal(filepath),Gdx.files.internal("art/particleEffects"));
        particleEffect.start();

    }

    @Override
    public String getID()
    {
        return "ParticleComponent";
    }

    @Override
    public Component cloneComponent()
    {
        return new ParticleComponent(filePath,effectName);
    }

    @Override
    protected void update(float dt) {
        particleEffect.setPosition(gameObject.getScreenPosition().x,gameObject.getScreenPosition().y);
        super.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb)
    {
        super.render(sb);

        if(runEffect)
        {
            if(repeat)
            {
                particleEffect.findEmitter(effectName).durationTimer = 0;
            }
            particleEffect.draw(sb,Gdx.graphics.getDeltaTime());
            if(particleEffect.isComplete())
            {
                runEffect = false;
            }
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isRunEffect() {
        return runEffect;
    }

    public void setRunEffect(boolean runEffect) {
        this.runEffect = runEffect;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
