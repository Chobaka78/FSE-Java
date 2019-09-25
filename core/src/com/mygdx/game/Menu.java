package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Menu {

    static Sprite Play, Instructions, Quit;
    ArrayList<Texture> tmp, tmp1, tmp2;
    static int choice = 0, choice1 = 0, choice2 = 0;
    private Texture background;
    Music music;

    public Menu(){
        Play = new Sprite();
        Instructions = new Sprite();
        Quit = new Sprite();

        tmp = new ArrayList<Texture>();
        tmp.add(new Texture("Assets/Buttons/Play/play0.png"));
        tmp.add(new Texture("Assets/Buttons/Play/play1.png"));

        tmp1 = new ArrayList<Texture>();
        tmp1.add(new Texture("Assets/Buttons/Instructions/instructions0.png"));
        tmp1.add(new Texture("Assets/Buttons/Instructions/instructions0.png"));

        tmp2 = new ArrayList<Texture>();
        tmp2.add(new Texture("Assets/Buttons/Quit/quit0.png"));
        tmp2.add(new Texture("Assets/Buttons/Quit/quit0.png"));

        background = new Texture("Assets/Backgrounds/Mainmenu.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/Mainmenu.mp3"));


    }

    public void render(SpriteBatch batch){
        batch.draw(background,0,0);
        Play.draw(batch);
        Instructions.draw(batch);
        Quit.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y){
        Play.set(new Sprite(tmp.get(choice)));
        Instructions.set(new Sprite(tmp1.get(choice1)));
        Quit.set(new Sprite(tmp2.get(choice2)));

        Play.setPosition(0,0);
        Instructions.setPosition(x - 120,y - 90);
        Quit.setPosition(x - 15,y - 190);
        render(batch);
    }
}
