package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

public class Utils {
    static int x, y;
    static Sprite Font;
    ShapeRenderer shape;
    Music music,level1;
    ArrayList<ArrayList<Texture>> Fonts = new ArrayList<ArrayList<Texture>>();
    ArrayList<Texture> play;
    static int choice;
    public Utils(){
        this.x = x;
        this.y = y;
        Font = new Sprite();
        music = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/Mainmenu.mp3"));

        play = new ArrayList<Texture>();
        play.add(new Texture("Assets/Buttons/Play/play0.png"));
        play.add(new Texture("Assets/Buttons/Play/play1.png"));
        Fonts.add(play);

    }

    public void render(SpriteBatch batch){
        Font.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y){
        Font.set(new Sprite(Fonts.get(0).get(choice)));
        Font.setPosition(x,y);
        render(batch);
    }
}
