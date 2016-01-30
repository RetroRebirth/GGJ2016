package com.teamname.goaton;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.teamname.goaton.Scenes.TestScene;
import com.teamname.goaton.Shaders.ShaderLoader;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;


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
	public void create ()
	{
        Box2D.init();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        batch = new SpriteBatch();

        testProgram =  ShaderLoader.LoadShader("shaders/default.vert", "shaders/default.frag");

		camera = new OrthographicCamera(1280, 720);
		viewport = new StretchViewport(1280,720,camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		GoatonWorld.worldHeight = viewport.getWorldHeight();
		GoatonWorld.worldWidth = viewport.getWorldWidth();

        Scene scene = new TestScene();
		GoatonWorld.setScene(scene);
	}

	@Override
	public void render ()
	{

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
        update();
		batch.begin();

        GoatonWorld.updateRender(1/60.f, batch,camera);
        batch.end();


    }
    public void update()
    {

        mgr.update(1/60.0f);

	}

	@Override
	public void resize(int width, int height)
	{
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

}
