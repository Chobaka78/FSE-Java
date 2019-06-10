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
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    private int Stance_frame = 0;
    int t = 0;

    static int[] gstat = {8000, 300, 8000, 200}; // hp,ki,attack,def
    static int[] vstat = {7000, 500, 10000, 150};
    static int[] gostat = {6500, 350, 300, 300};
    static int[] fstat = {50000, 500, 500, 400};

    public Goku(){
        Goku = new Sprite();



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
    }
    public void render(SpriteBatch batch){
        Goku.draw(batch);

    }

    public int moveStance() { // this is the animation for the stance of the character
        if (Stance_frame < 4) {
            if (t < 4) {
                t++;
                if (t == 4) {
                    Stance_frame += 1;
                    if (Stance_frame == 4) {
                        Stance_frame = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return Stance_frame;
    }

    public void update(SpriteBatch batch, int x, int y){

        if(Battle.turn.equals("goku") && Battle.Person.equals("Player")){
            Goku.set(new Sprite(sprites.get(Battle.type).get(Battle.frame)));
        }
        else{
            Goku.set(new Sprite(sprites.get(0).get(0)));
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
