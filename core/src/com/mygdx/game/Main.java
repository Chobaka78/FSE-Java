package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Player goku;
	Utils utils;
	int mx,my;
	public static int moves, type;
	public static final int Attack = 0, Kick = 1;
	static boolean animation;
	static String Game = "Menu", Mode; // this is a String that will determine what the current mode is(main menu, level, etc.)
    Rectangle rect;

	@Override
	public void create () {
		batch = new SpriteBatch();
		goku = new Player(200,200);
		utils = new Utils();
		background = new Texture("Assets/Backgrounds/Mainmenu.png");

	}

	@Override
	public void render () {
        if (Game.equals("Menu")) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            utils.music.play();
            mx = Gdx.input.getX();
            my = Gdx.input.getY();
            rect = new Rectangle(mx,my,1,1);
            if(Utils.Font.getBoundingRectangle().overlaps(rect)){
                Utils.choice = 1;
            }
            else{
                Utils.choice = 0;
            }
            batch.begin();
            batch.draw(background, 0, 0);
            utils.update(batch,10,245);
            batch.end();
            if (Utils.Font.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                utils.music.stop();
                Game = "Level1";
            }
        }
        if (Game.equals("Level1")) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                moves = Attack;
                animation = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.B)) {

                moves = Kick;
                animation = true;
            }
            batch.begin();
            goku.update(batch);
            batch.end();
        }
    }

	@Override
	public void dispose (){
		batch.dispose();
		utils.music.dispose();
	}
}
