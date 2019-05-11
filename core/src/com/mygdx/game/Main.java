package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.game.Utils.Font;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture city;
	Player goku;
	Utils utils;
	Map tiledMap;
	Open_Player world;
	private int mx,my, vx, vy;
	public static int moves;
	public static final int Attack = 0, Kick = 1;
	static boolean animation;
	static String Game = "Menu", Mode; // this is a String that will determine what the current mode is(main menu, level, etc.)
    Rectangle rect;
    public static OrthographicCamera camera;
    public static final int UP = 0, Down = 1, Left = 2, Right = 3;
    static boolean animation1;
    public static int moves1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		goku = new Player(200,200);
		utils = new Utils();
		world = new Open_Player(200,200);
		background = new Texture("Assets/Backgrounds/Mainmenu.png");
		city = new Texture("Assets/Backgrounds/city.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false,0,0);
        camera.update();

	}

	@Override
	public void render () {
        if (Game.equals("Menu")) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            utils.music.play();
            mx = Gdx.input.getX();
            my = Gdx.input.getY();
            rect = new Rectangle(mx,my,1,1); // mouse rect made for collision (1 by 1 square)
            if(Utils.Font.getBoundingRectangle().overlaps(rect)){
                Utils.choice = 1;
            }
            else{
                Utils.choice = 0;
            }
            batch.begin();
            batch.draw(background, 0, 0);
            utils.update(batch,10,265);
            batch.end();
            if (Font.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                utils.music.stop();
                Game = "Level1";
            }
        }
        if (Game.equals("Level1")) {
            Gdx.gl.glClearColor(225,225,225,0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            tiledMap = new Map();
            tiledMap.render(camera);
            mx = Gdx.input.getX();
            my = Gdx.input.getY();
            System.out.println(mx + ", " + my);

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                moves = Attack;
                animation = true;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.B)) {

                moves = Kick;
                animation = true;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                moves1 = UP;
                vy +=5;
                animation1 = true;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                moves1 = Down;
                vy -=5;
                animation1 = true;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                moves1 = Left;
                vx -= 5;
                animation1 = true;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                moves1 = Right;
                vx +=5;
                animation1 = true;
            }
            else{
                animation1 = false;
                world.frames = 0;
            }
            batch.begin();
            world.update(batch, vx, vy);
            batch.end();
        }
    }

	@Override
	public void dispose (){
		batch.dispose();
		utils.music.dispose();
	}
}
