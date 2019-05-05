package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player goku;
	int elapsed = 0;
	boolean animation;
	public static final int Attack = 0, Kick = 1;
	public int move;

	@Override
	public void create () {
		batch = new SpriteBatch();
		goku = new Player(200,200);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			move = Attack;
			animation = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.B)){
			move = Kick;
			animation = true;
		}

		if(animation && move == Attack){
			if(elapsed < 5) {
				elapsed += 1;
				if (elapsed == 5) {
					Player.fstat[0] = Player.fstat[0] - Player.gstat[2];
					elapsed = 0;
					animation = false;

				}
			}
		}
		else if (animation && move == Kick){
			if(elapsed < 5) {
				elapsed += 1;
				if (elapsed == 5) {
					elapsed = 0;
					animation = false;

				}
			}
		}
		else{
			elapsed = 0;
		}

		batch.begin();
		System.out.println(Player.fstat[0]);
		Player.sprites.get(move).get(elapsed).draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
