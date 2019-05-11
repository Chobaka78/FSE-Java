package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {

    int width  = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

    private TmxMapLoader mapLoader;
    private com.badlogic.gdx.maps.tiled.TiledMap map;
    static OrthogonalTiledMapRenderer renderer;
    private static OrthographicCamera camera;

    public Map(){

        camera = new OrthographicCamera();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
    }
    public void render(OrthographicCamera camera){
        camera.setToOrtho(false,width,height);
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

}
