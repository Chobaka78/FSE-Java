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
    Texture attackf;
    Texture cont;
    Texture title;
    static Sprite attacks;
    static Sprite contin;
    static Sprite t;
    Music bossbattle ;
    Music music,level1;
    Music worldmusic;
    Music victory;
    ArrayList<ArrayList<Texture>> Fonts = new ArrayList<ArrayList<Texture>>();
    ArrayList<Texture> tmp;
    static int choice;
    public Utils(){
        this.x = x;
        this.y = y;
        Font = new Sprite();
        music = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/Mainmenu.mp3"));
        bossbattle = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/boss.mp3"));
        worldmusic =  Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/opensong.mp3"));
        victory = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/victory.mp3"));
        tmp = new ArrayList<Texture>();
        tmp.add(new Texture("Assets/Buttons/Play/play0.png"));
        tmp.add(new Texture("Assets/Buttons/Play/play1.png"));
        Fonts.add(tmp);
        attackf = new Texture("Assets/Fonts/attack.png");
        attacks = new Sprite(attackf);
        cont = new Texture("Assets/Fonts/cont.png");
        contin =  new Sprite(cont);
        title = new Texture("Assets/Fonts/title.png");
        t = new Sprite(title);

    }

    public void render(SpriteBatch batch){
        Font.draw(batch);
        t.draw(batch);

    }

    public void update(SpriteBatch batch,int x, int y){
        Font.set(new Sprite(Fonts.get(0).get(choice)));
        attacks.setPosition(200,50);
        contin.setPosition(360,50);
        t.setPosition(440,500);
        Font.setPosition(x,y);


        render(batch);
    }
}
