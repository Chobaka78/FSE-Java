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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


import java.io.IOException;

import static com.mygdx.game.Utils.*;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private Texture background;

    private Texture city;

    private Texture stage;

    private Texture over;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Player player;

	static Utils utils;

	private Frieza frieza_open;

	private Enemy frieza;

    static WorldCreator worldcreator;

	int mx, my, speed = 5000;

	static String mode = "";

	public static final int Attack = 0;

//	public static final float PPM = 1;

    public static int movesg;

    public static int movesv =0;
    public static int movef =0;
    public static int movego = 2;
    static boolean animation;

	static String Game = "Menu"; // this is a String that will determine what the current mode is(main menu, level, etc.)

    static Rectangle rect;

    public static OrthographicCamera camera;

    public static final int UP = 0, Down = 1, Left = 2, Right = 3;
    static boolean animation1;
    public static int moves1;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public static World world;

    Box2DDebugRenderer b2dr;


	@Override
	public void create () {
        world = new World(new Vector2(0,0),true);
        batch = new SpriteBatch();
		goku = new Goku(200,200);
		vegeta = new Vegeta(600,100);
		utils = new Utils();
		player = new Player();
		frieza_open = new Frieza();
        gohan = new Gohan (600,50);
        frieza = new Enemy(700,300);

		background = new Texture("Assets/Backgrounds/Mainmenu.png");
		city = new Texture("Assets/Backgrounds/city.png");
		stage = new Texture("Assets/Backgrounds/stage.png");
        over = new Texture("Assets/Backgrounds/gameover.png");


        mapLoader = new TmxMapLoader();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1100f,660f);
        map = mapLoader.load("Assets/Maps/World map.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        worldcreator = new WorldCreator(world,map);

        world.setContactListener(new WorldContactListener());

        b2dr = new Box2DDebugRenderer();

	}

	@Override
	public void render () {
        System.out.println(mode + ", " + (int)player.getBody().getPosition().x +", " +  (int)player.getBody().getPosition().y);
        if (Game.equals("Menu")) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            utils.music.play();
            mx = Gdx.input.getX();
            my = Math.abs(660 - Gdx.input.getY());
            rect = new Rectangle(mx,my,1,1); // mouse rect made for collision (1 by 1 square)
            if(Utils.Font.getBoundingRectangle().overlaps(rect)){
                Utils.choice = 1;
            }
            else{
                Utils.choice = 0;
            }
            batch.begin();
            batch.draw(background, 0, 0);
            utils.update(batch,480,265);
            batch.end();
            if (Font.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                utils.music.stop();
                Game = "Level1";
                mode = "open";

            }
        }

        if (mode.equals("battle")) {
            utils.worldmusic.stop();

            mode = "battle";
            battle.battle();

            batch.begin();
            batch.draw(stage,0,0);
            frieza.update(batch,400,300);
            goku.update(batch,600,200);
            vegeta.update(batch,600,300);
            gohan.update(batch , 600 , 150);

            Utils.attacks.draw(batch);
            batch.end();
        }

        if (Goku.fstat[0] <0 && mode != "open"){
            utils.bossbattle.stop();
            utils.victory.play();

            mode = "win";

            batch.begin();
            batch.draw(over, 0, 0);
            rect = new Rectangle (360,50,1,1);
            Utils.contin.draw(batch);

            batch.end();
            if (contin.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                mode = "open";
            }


        }

        if (Game.equals("Level1")&& mode.equals ("open")) {
            //camera.zoom = PPM;
            //Rendering the map
            world.step(1/60f,6,2);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();

            renderer.setView(camera);
            renderer.render();
            batch.setProjectionMatrix(camera.combined);

            b2dr.render(world,camera.combined);

            utils.worldmusic.play();

            mx = Gdx.input.getX();
            my = Gdx.input.getY();

            batch.begin();
            update();
            batch.end();
            move();
        }
    }

    public void update(){
        player.update(batch);
        frieza_open.update(batch);
    }

    public void move(){
        player.body.setLinearVelocity(0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            moves1 = UP;
            player.getBody().applyLinearImpulse(new Vector2(0,speed*2), player.getBody().getWorldCenter(),true);
            animation1 = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moves1 = Down;
            player.getBody().applyLinearImpulse(new Vector2(0,-speed*2), player.getBody().getWorldCenter(),true);
            animation1 = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moves1 = Left;
            player.getBody().applyLinearImpulse(new Vector2(-speed*2,0), player.getBody().getWorldCenter(),true);
            animation1 = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moves1 = Right;
            player.getBody().applyLinearImpulse(new Vector2(speed*2,0), player.getBody().getWorldCenter(),true);
            animation1 = true;
        }
        else{
            player.getBody().applyLinearImpulse(new Vector2(player.getBody().getLinearVelocity().x * -1, player.getBody().getLinearVelocity().y * -1), player.getBody().getWorldCenter(), true);
            animation1 = false;
            player.frames = 0;
        }

//        player.setX(player.body.getPosition().x);
//        player.setY(player.body.getPosition().y);
//
//        camera.position.x = player.getX();
//        camera.position.y = player.getY();
    }

	@Override
	public void dispose (){
		batch.dispose();
		utils.music.dispose();
		renderer.dispose();
	}
}
