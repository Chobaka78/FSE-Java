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
    int [] list = new int []{7,7};
    private static int frame = 0;
    int t = 0, r =0;

    static int[] gstat = {8000, 300, 8000, 200}; // hp,ki,attack,def
    static int[] vstat = {7000, 500, 10000, 150};
    static int[] gostat = {6500, 350, 300, 300};
    static int[] fstat = {50000, 500, 500, 400};

    public Goku(int x, int y){
        this.x = x;
        this.y = y;
        Goku = new Sprite();

        // loading for Stance goku
        for(int i = 0; i < 1; i ++){
            tmpg1 = new ArrayList<Texture>();
            for (int m = 0; m < 4; m ++){
                tmpg1.add(new Texture("Assets/Sprites/Goku/Stance/Stance" + m + ".png"));
            }
            sprites.add(tmpg1);
        }

        for(int k = 0; k < list.length; k ++) {
            for (String i : new String[]{"attackg","kameg"}){
                tmpg = new ArrayList<Texture>();
                for (int j = 0; j < list[k]; j++) {
                    tmpg.add(new Texture("Assets/Sprites/Goku/" + i + "/" + i + j + ".png"));
                }
                sprites.add(tmpg);
            }
        }
    }
    public void render(SpriteBatch batch){
        Goku.draw(batch);

    }

    public int moveFrames(){ // this is the animation for the movement of the character
        if(frame < list[Main.movesg]){
            //System.out.println("FAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACCCCCCCCCCCCCCCCCCCCTTTTTTTTS");
            if(r < 3) {
                r ++;
                if(r == 3) {
                    frame += 1;
                    if (frame == list[Main.movesg]) {
                        System.out.println(" it is " + battle.turn+"turn");
                        fstat[0] = fstat[0] + ((fstat[3]*1/2) - gstat[2]);
                        frame = 0;
                        System.out.println(fstat[0]);
                        Main.animation = false;
                        battle.turn = "vegeta";
                        Main.movesg = 2;

                    }
                    r = 0;
                }
            }
        }

        return frame;
    }

    public int moveStance(){ // this is the animation for the stance of the character
        if(frame < 4) {
            if (t < 4) {
                t++;
                if (t == 4) {
                    frame += 1;
                    if (frame == 4) {
                        frame = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return frame;
    }
    public void update(SpriteBatch batch, int x, int y){
        if(Main.animation && Main.movesg == Main.Attack){
            moveFrames();
        }
        else{
            moveStance();
        }
        Goku.set(new Sprite(sprites.get(Main.movesg).get(frame)));
        Goku.setPosition(500,300);
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
