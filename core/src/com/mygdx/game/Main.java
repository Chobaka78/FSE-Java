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


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mygdx.game.Utils.*;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

    private Texture city;

    private Texture over;

    private Texture gameover;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Player player;

    private Menu menu;

	static Utils utils;

	private OpenEnemy frieza_open;

	private Enemy frieza;

	private Battle battle;

	private Items item;

    static WorldCreator worldcreator;

	int mx, my, currentX, currentY;

	static String mode = "", type = "";

	public static final float PPM = 0.3f;

    static boolean animation, shop = false, moveBody, House = false, Weirdplace = false, Map2 = false;

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

    public static int EnemyType = 0;




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

        item = new Items();

		city = new Texture("Assets/Backgrounds/city.png");
        over = new Texture("Assets/Backgrounds/gameover.png");
       // gameover = new Texture(" Assets/Backgrounds/quit.png");


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
                try {
                    File file = new File("Assets/Instructions.txt");
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("Desktop is not supported");
                        return;
                    }

                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch (IOException var4) {
                    System.out.println("FILE NOT FOUND");
                }

            }
            else if(Menu.Quit.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                Gdx.app.exit();
            }

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
        if (mode.equals("gameover")){
            batch.begin();
            batch.draw(gameover, 0, 0);
            rect = new Rectangle (360,50,1,1);
            Utils.contin.draw(batch);

            batch.end();
            if (contin.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                mode = "open";
            }



        }

        if (Game.equals("Level1")&& mode.equals ("open")) {

            if(Battle.onestar && Battle.twostar && Battle.threestar && Battle.fourstar && Battle.fivestar && Battle.sixstar && Battle.sevenstar && player.getX() > 144 && player.getX() < 167 && player.getY() > 250 && Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                Gdx.app.exit();
            }

            camera.zoom = 0.1f;
            //Rendering the map
            world.step(1/60f,6,2);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();

            renderer.setView(camera);
            renderer.render();

            batch.setProjectionMatrix(camera.combined);

//            b2dr.render(world,camera.combined);

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
        if(shop){
            item.update(batch);
        }


        if(Battle.onestar){
            batch.draw(Items.oneStar,64,300,Items.oneStar.getWidth() *(float) Math.pow(PPM,2), Items.oneStar.getHeight() *(float) Math.pow(PPM,2));
        }

        if(Battle.twostar){
            batch.draw(Items.TwoStar,95,300,Items.TwoStar.getWidth() *(float) Math.pow(PPM,2), Items.TwoStar.getHeight() *(float) Math.pow(PPM,2));
        }

        if(Battle.threestar){
            batch.draw(Items.ThreeStar,130,300,Items.ThreeStar.getWidth() *(float) Math.pow(PPM,2), Items.ThreeStar.getHeight() *(float) Math.pow(PPM,2));
        }

        if(Battle.fourstar){
            batch.draw(Items.FourStar,174,300,Items.FourStar.getWidth() *(float) Math.pow(PPM,2), Items.FourStar.getHeight() *(float) Math.pow(PPM,2));
        }

        if(Battle.fivestar){
            batch.draw(Items.FiveStar,205,300,Items.FiveStar.getWidth() *(float) Math.pow(PPM,2), Items.FiveStar.getHeight() *(float) Math.pow(PPM,2));
        }
        if(Battle.sixstar){
            batch.draw(Items.SixStar,232,300,Items.SixStar.getWidth() *(float) Math.pow(PPM,2), Items.SixStar.getHeight() *(float) Math.pow(PPM,2));
        }
        if(Battle.sevenstar){
            batch.draw(Items.SevenStar,262,300,Items.SevenStar.getWidth() *(float) Math.pow(PPM,2), Items.SevenStar.getHeight() *(float) Math.pow(PPM,2));

        }


        player.update(batch);
        frieza_open.update(batch);
    }

    public void move(){
        player.body.setLinearVelocity(0,0);

        if(!Items.HitTrunks) {

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                moves1 = UP;
                player.getBody().applyLinearImpulse(new Vector2(0, 100), player.getBody().getWorldCenter(), true);
                animation = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                moves1 = Down;
                player.getBody().applyLinearImpulse(new Vector2(0, -100), player.getBody().getWorldCenter(), true);
                animation = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moves1 = Left;
                player.getBody().applyLinearImpulse(new Vector2(-100, 0), player.getBody().getWorldCenter(), true);
                animation = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moves1 = Right;
                player.getBody().applyLinearImpulse(new Vector2(100, 0), player.getBody().getWorldCenter(), true);
                animation = true;
            } else {
                player.getBody().applyLinearImpulse(new Vector2(player.getBody().getLinearVelocity().x * -1, player.getBody().getLinearVelocity().y * -1), player.getBody().getWorldCenter(), true);
                animation = false;
                player.frames = 0;
            }
        }

        player.setX(player.body.getPosition().x);
        player.setY(player.body.getPosition().y);

        // the following if statement is to apply a boundary on the camera

        if(!shop && !moveBody && !House && !Weirdplace && !Map2) {
            if (player.body.getPosition().x > 54 && player.body.getPosition().x < 275) {
                camera.position.x = player.getX();

            }
            if (player.body.getPosition().y > 33 && player.body.getPosition().y < 165) {
                camera.position.y = player.getY();
            }
        }
        else if(moveBody){
            if(type.equals("Shop")) {
                player.MoveBody(726, 100);
                shop = true;
                camera.position.x = 726;
                camera.position.y = 17;
            }

            if(type.equals("House")) {
                currentX = (int)player.getX();
                currentY = (int)player.getY();
                player.MoveBody(839, 103);
                House = true;
                camera.position.x = 839;
                camera.position.y = 103;
            }

            if(type.equals("WeirdPlace")) {
                player.MoveBody(156, 225);
                Weirdplace = true;
                camera.position.x = 156;
                camera.position.y = 231;
            }

            if(type.equals("Map2")) {
                player.MoveBody(354, 98);
                Map2 = true;
                camera.position.x = 392;
                camera.position.y = 98;
            }

            else if(type.equals("open")){
                if(shop) {
                    player.MoveBody(110, 113);
                    shop = false;
                    camera.position.x = 110;
                    camera.position.y = 17;
                }

                else if(House){ // this sets all the camera and player corrdinated after you exist the house (so that boundries are maintained)
                    player.MoveBody(currentX, currentY);
                    House = false;
                    if(player.body.getPosition().y < 33) {
                        camera.position.y = 33;
                    }
                    else{
                        camera.position.y = currentY;
                    }
                    if(player.body.getPosition().x > 275 && player.body.getPosition().x < 343){
                        camera.position.x = 275;
                    }
                    else if(player.body.getPosition().x > 359 && player.body.getPosition().x < 372){
                        camera.position.x = 394;
                    }
                    else if(player.body.getPosition().x > 605 && player.body.getPosition().x < 645){
                        camera.position.x = 611;
                    }
                    else {
                        camera.position.x = currentX;
                    }
                }

                else if(Weirdplace){
                    player.MoveBody(156 , 189);
                    Weirdplace = false;
                    camera.position.x = 156;
                    if(player.body.getPosition().y > 165) {
                        camera.position.y = 165;
                    }
                    else{
                        camera.position.y = 189;
                    }
                }

                else if(Map2){
                    player.MoveBody(323,98);
                    Map2 = false;
                    camera.position.x = 275;
                    camera.position.y = player.getY();
                }
            }
            moveBody = false;
        }

        if(shop){
            if(player.body.getPosition().y > 17 && player.body.getPosition().y < 143) {
                camera.position.y = player.getY();
            }
        }

        if(House){
            if(player.body.getPosition().y > 103 && player.body.getPosition().y <  200) {
                camera.position.y = player.getY();
            }
        }

        if(Weirdplace){
            if(player.body.getPosition().x > 60 && player.body.getPosition().x < 277){
                camera.position.x = player.getX();

            }
            if(player.body.getPosition().y > 231 && player.body.getPosition().y < 358) {
                camera.position.y = player.getY();
            }
        }

        if(Map2){
            if(player.body.getPosition().x > 392 && player.body.getPosition().x < 611) {
                camera.position.x = player.getX();
            }
            if (player.body.getPosition().y > 33 && player.body.getPosition().y < 165) {
                camera.position.y = player.getY();
            }
        }
    }

	@Override
	public void dispose (){
		batch.dispose();
		utils.music.dispose();
		renderer.dispose();
	}
}

//reupload
