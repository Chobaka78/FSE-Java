package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


import java.util.ArrayList;
import java.util.Arrays;

import static com.mygdx.game.Utils.*;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

    private Texture city;

    private Texture over;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Player player;

    private Menu menu;

	static Utils utils;

	private OpenEnemy frieza_open;

	private Enemy frieza;

	private Battle battle;

    static WorldCreator worldcreator;

	int mx, my;

	static String mode = "";

	public static final float PPM = 0.3f;

    static boolean animation;

	static String Game = "Menu"; // this is a String that will determine what the current mode is(main menu, level, etc.)

    static Rectangle rect;

    public static OrthographicCamera camera;

    public static final int UP = 0, Down = 1, Left = 2, Right = 3;

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

		goku = new Goku();

		vegeta = new Vegeta();

		utils = new Utils();

		player = new Player();

		frieza_open = new OpenEnemy();

        gohan = new Gohan ();

        frieza = new Enemy();

        battle = new Battle();

        menu = new Menu();

		city = new Texture("Assets/Backgrounds/city.png");
        over = new Texture("Assets/Backgrounds/gameover.png");


        mapLoader = new TmxMapLoader();

        camera = new OrthographicCamera(1100f,660f);

        map = mapLoader.load("Assets/Maps/Map.tmx");

        renderer = new OrthogonalTiledMapRenderer(map,PPM);

        worldcreator = new WorldCreator(world,map);

        world.setContactListener(new WorldContactListener());

        b2dr = new Box2DDebugRenderer();

	}

	@Override
	public void render () {
       // System.out.println(Battle.turn +", " + Battle.type + ", " + Battle.Person + ", " + Battle.frame);
        System.out.println(Player.Goku.getX() + ", " + Player.Goku.getY());
        if (Game.equals("Menu")) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            menu.music.play();

            mx = Gdx.input.getX();
            my = Math.abs(660 - Gdx.input.getY());
            rect = new Rectangle(mx,my,1,1); // mouse rect made for collision (1 by 1 square)
            if(Menu.Play.getBoundingRectangle().overlaps(rect)){
                Menu.choice = 1;
            }
            else if(Menu.Instructions.getBoundingRectangle().overlaps(rect)){
                Menu.choice1 = 1;
            }

            else if(Menu.Quit.getBoundingRectangle().overlaps(rect)){
                Menu.choice2 = 1;
            }
            else{
                Menu.choice = 0;
                Menu.choice1 = 0;
                Menu.choice2 = 0;
            }

            batch.begin();
            menu.update(batch,480,355);
            batch.end();
            if (Menu.Play.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                menu.music.stop();
                Game = "Level1";
                mode = "open";

            }

            else if (Menu.Instructions.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                System.out.println("Instructions");

            }
            else if(Menu.Quit.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                Gdx.app.exit();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)){
            Battle.enemy = 4;
            mode ="battle";

        }

        if (mode.equals("battle")) {


            camera.zoom = 1f;

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.setProjectionMatrix(camera.combined);

            camera.position.x = 550;
            camera.position.y = 330;

            utils.worldmusic.stop();

            batch.begin();
            battle.update(batch,100,50);
            batch.end();

        }



        if (Goku.fstat[0] <0 && mode != "open"){

            Battle.bossbattle.stop();
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
            camera.zoom = 0.1f;
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
            player.getBody().applyLinearImpulse(new Vector2(0,100), player.getBody().getWorldCenter(),true);
            animation = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moves1 = Down;
            player.getBody().applyLinearImpulse(new Vector2(0,-100), player.getBody().getWorldCenter(),true);
            animation = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moves1 = Left;
            player.getBody().applyLinearImpulse(new Vector2(-100,0), player.getBody().getWorldCenter(),true);
            animation = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moves1 = Right;
            player.getBody().applyLinearImpulse(new Vector2(100,0), player.getBody().getWorldCenter(),true);
            animation = true;
        }
        else{
            player.getBody().applyLinearImpulse(new Vector2(player.getBody().getLinearVelocity().x * -1, player.getBody().getLinearVelocity().y * -1), player.getBody().getWorldCenter(), true);
            animation = false;
            player.frames = 0;
        }

        player.setX(player.body.getPosition().x);
        player.setY(player.body.getPosition().y);

        // the following if statement is to apply a boundary on the camera
        if(player.body.getPosition().x > 63 && player.body.getPosition().x < 617) {
            camera.position.x = player.getX();

        }
        if(player.body.getPosition().y > 42 && player.body.getPosition().y < 165){
            camera.position.y = player.getY();
        }
    }

	@Override
	public void dispose (){
		batch.dispose();
		utils.music.dispose();
		renderer.dispose();
	}
}
