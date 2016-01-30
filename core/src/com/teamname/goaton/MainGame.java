package com.teamname.goaton;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.teamname.goaton.Shaders.ShaderLoader;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;
import com.teamname.goaton.components.InputComponent;
import com.teamname.goaton.components.ScoreComponent;


public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;

	Sprite img;
    static TweenManager mgr = new TweenManager();
    ShaderProgram testProgram;

    //Message test
    GameObject go1, go2;

	@Override
	public void create () {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        batch = new SpriteBatch();

        testProgram =  ShaderLoader.LoadShader("shaders/default.vert", "shaders/default.frag");

	    img = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        batch.setShader(testProgram);
        Tween.to(img,SpriteAccessor.TWEEN_XY,1.0f).target(100,100).repeatYoyo(10,0).start(mgr);
        go1 = new GameObject();
        go2 = new GameObject();
        go1.addComponent(new InputComponent());
        go2.addComponent(new ScoreComponent());

        World.addObject(go1);
        World.addObject(go2);


        img = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		img.setSize(100,100);
		img.setPosition(0,0);

        Tween.to(img,SpriteAccessor.TWEEN_XY,1.0f).target(img.getX() + 1180,img.getY() +
				620)
				.repeatYoyo(10,0)
				.start(mgr);

		camera = new OrthographicCamera();
		viewport = new StretchViewport(1280,720,camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        World.updateRender(1/60.f, batch);
		img.draw(batch);
        batch.end();


    }
    public void update()
    {

        mgr.update(1/60.0f);

	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

}
