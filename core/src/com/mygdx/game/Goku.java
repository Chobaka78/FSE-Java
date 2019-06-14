package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

import javax.swing.text.Position;
import java.io.IOException;
import java.util.ArrayList;

public class Goku {
    static Sprite Goku;
    private static int x,y;
    static ArrayList<Texture> tmpg, tmpg1;
    static Texture def;
    static Texture death;

    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    static ArrayList<ArrayList<Texture>> spritesd = new ArrayList<ArrayList<Texture>>();
    private int Stance_frame = 0;
    int t = 0;


    static int[] gstat = {8000, 300, 8000, 200}; // hp,ki,attack,def
    static int[] vstat = {7000, 500, 10000, 150};
    static int[] gostat = {6500, 350, 300, 300};
    static int[] fstat = {50000, 500, 500, 400};
    static boolean defend = false;


    public Goku(){
        Goku = new Sprite();
        def = new Texture("Assets/Sprites/Goku/defendg/defendg0.png");
        death = new Texture("Assets/Sprites/Goku/deathg/deathg0.png");





            for (String i : new String[]{"attackg"}){
                tmpg = new ArrayList<Texture>();
                for (int j = 0; j < 7; j++) {
                    tmpg.add(new Texture("Assets/Sprites/Goku/" + i + "/" + i + j + ".png"));
                }
                sprites.add(tmpg);
            }

        for (String i : new String[]{"Special"}){
            tmpg = new ArrayList<Texture>();
            for (int j = 0; j < 9; j++) {
                tmpg.add(new Texture("Assets/Sprites/Goku/" + i + "/" + i + j + ".png"));
            }
            sprites.add(tmpg);
        }

        for (String i : new String[]{"gokuheal"}){
            tmpg = new ArrayList<Texture>();
            for (int j = 0; j < 8; j++) {
                tmpg.add(new Texture("Assets/Sprites/Goku/" + i + "/" + i + j + ".png"));
            }
            sprites.add(tmpg);
        }


    }
    public void render(SpriteBatch batch){
        Goku.draw(batch);

    }


    public void update(SpriteBatch batch, int x, int y){

        if(Battle.turn == Battle.GOKU && Battle.Person.equals("Player")){
            defend = false;

            Goku.set(new Sprite(sprites.get(Battle.type).get(Battle.frame)));

        }
        else{

            Goku.set(new Sprite(sprites.get(0).get(0)));


        }
        if (Battle.g.equals("dead")){
            Goku.set(new Sprite(death));
        }
        if(defend){
            Goku.set(new Sprite(def));

        }
        Goku.setPosition(x,y);
        render(batch);


    }

    public static int getX(){
        return x;
    }

    // gets the y of player
    public static int getY(){
        return y;
    }

    // sets the x of player
    public void setX(int x){
        this.x = x;
    }

}
