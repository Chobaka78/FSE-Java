package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Gohan {
    static Sprite G;
    private static int x, y;
    static ArrayList<Texture> tmpgo, tmpgo1;
    static ArrayList<ArrayList<Texture>> spritesgo = new ArrayList<ArrayList<Texture>>();
    int[] listgo = new int[]{7, 7};
    private String[] movementgo = new String[]{"attackgo", "kamego"};
    private static int frame = 0;
    int t = 0;

    public Gohan(int x, int y) {
        this.x = x;
        this.y = y;
        G = new Sprite();

        for(int i = 0; i < 1; i ++){
            tmpgo1 = new ArrayList<Texture>();
            for (int m = 0; m < 4; m ++){
                tmpgo1.add(new Texture("Assets/Sprites/Gohan/Stance/Stance" + m + ".png"));
            }
            spritesgo.add(tmpgo1);
        }
        
        for (int l = 0; l < listgo.length; l++) {
            for (String h : movementgo) {
                tmpgo = new ArrayList<Texture>();
                for (int v = 0; v < listgo[l]; v++) {
                    tmpgo.add(new Texture("Assets/Sprites/Gohan/" + h + "/" + h + v + ".png"));
                }
                spritesgo.add(tmpgo);
            }


        }
    }
    public void render (SpriteBatch batch){
        G.draw(batch);

    }
    public int moveframesgo(){
        if(frame < listgo[Main.movego] ){
            if(t < 3) {
                t ++ ;
                if(t == 3) {
                    frame += 1;
                    if (frame == listgo[Main.movego]) {
                        System.out.println(" it is " + battle.turn+"turn");

                        Goku.fstat[0] = Goku.fstat[0] +(Goku.fstat[3]*1/2 - Goku.gostat[2]);
                        System.out.println("frieza health : " + Goku.fstat[0]);
                        frame = 0;
                        Main.animation = false;
                        battle.turn = "frieza";
                        Main.movego = 2;
                    }
                    t = 0;
                }
            }
        }

        return frame;
    }

    public int moveStance() { // this is the animation for the stance of the character
        if (frame < 4) {
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
    public void update(SpriteBatch batch, int x, int y) {
        if (Main.animation && Main.movego == Main.Attack) {

            moveframesgo();

        }
        else {
            moveStance();
        }
        G.set(new Sprite(spritesgo.get(Main.movesv).get(frame)));
        G.setPosition(700,150);
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
        Gohan.x = x;
    }


}





