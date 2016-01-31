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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.teamname.goaton.Scenes.TestScene;
import com.teamname.goaton.Shaders.ShaderLoader;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;
import com.teamname.goaton.components.SpriteRenderComponent;


public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;

    //map stuff
    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tileMapRenderer;

	Sprite img;
    ShaderProgram testProgram;

    //Message test
    GameObject go1, go2;

    Scene scene;

	@Override
	public void create ()
	{
        Box2D.init();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        batch = new SpriteBatch();
        GoatonWorld.TweenManager = new TweenManager();

        testProgram =  ShaderLoader.LoadShader("shaders/default.vert", "shaders/default.frag");

        scene = new TestScene();
		scene.getCamera().position.set(scene.getCamera().viewportWidth/2,scene.getCamera().viewportHeight/2,0);

        tileMap = new TmxMapLoader().load("art/testmap.tmx");
        tileMapRenderer = new OrthogonalTiledMapRenderer(tileMap);


		GoatonWorld.setScene(scene);
	}

	@Override
	public void render ()
	{

		Gdx.gl.glClearColor(0.35f,0.89f,0.89f,1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(scene.getCamera().combined);
        update();

        tileMapRenderer.setView((OrthographicCamera) scene.getCamera());
        tileMapRenderer.render();
        batch.begin();
        GoatonWorld.updateRender(1 / 60.f, batch);
        batch.end();

        //scene.getCamera().translate(1,1,0);
        scene.getCamera().update();

    }
    public void update()
    {

	}

	@Override
	public void resize(int width, int height)
	{
		scene.updateViewport(width, height);
	}

}
