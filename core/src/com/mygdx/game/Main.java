package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    public static final float PPM = 0.6f;
    public static OrthographicCamera camera;
    public static int moves1;
    static boolean animation, shop = false, moveBody, House = false, Weirdplace = false, Map2 = false;
    public static final int UP = 0, Down = 1, Left = 2, Right = 3, NW = 4, SW = 5, NE = 6;
    private Player p;

    static WorldCreator worldCreator;

    public static World world;

    private TiledMap map;

    private TmxMapLoader mapLoader;

    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void create() {
        world = new World(new Vector2(0,0),true);

        batch = new SpriteBatch();
        //img = new Texture("Assets/TitleScreen.png");
        img = new Texture("Assets/player assets/Lan's room.png");
        camera = new OrthographicCamera(720f, 480f);
        p = new Player();

        mapLoader = new TmxMapLoader();

        map = mapLoader.load("Assets/Maps/Map.tmx");

		renderer = new OrthogonalTiledMapRenderer(map,PPM);

        world.setContactListener(new WorldContactListener());

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.zoom = 0.3f;
        camera.update();
//		renderer.render();
        batch.setProjectionMatrix(camera.combined);
        System.out.println();


        batch.begin();
        batch.draw(img, -125, -110);
        update();
        batch.end();
        move();
    }

    public void update() {
        p.update(batch);
    }


    public void move() {
        //p.body.setLinearVelocity(0, 0);


        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {// moves the player up
            moves1 = UP;
            System.out.println("up");
            //p.getBody().applyLinearImpulse(new Vector2(0, 100), p.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moves1 = Down;
            System.out.println("down");
            //p.getBody().applyLinearImpulse(new Vector2(0, -100), p.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moves1 = Left;
            System.out.println("left");
//			p.getBody().applyLinearImpulse(new Vector2(-100, 0), p.getBody().getWorldCenter(), true);
            animation = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moves1 = Right;
            System.out.println("right");
            //p.getBody().applyLinearImpulse(new Vector2(100, 0), p.getBody().getWorldCenter(), true);
            animation = true;
        } else {
            //p.getBody().applyLinearImpulse(new Vector2(p.getBody().getLinearVelocity().x * -1, p.getBody().getLinearVelocity().y * -1), p.getBody().getWorldCenter(), true);
            animation = false;
            p.frames = 0;
        }

//	p.setX(p.body.getPosition().x);
//	p.setY(p.body.getPosition().y);

//	p.MoveBody(726,100);
    }



    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
