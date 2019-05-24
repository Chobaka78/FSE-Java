package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Gohan {
    static Sprite G;
    private static int x, y;
    static ArrayList<Texture> tmpgo;
    static ArrayList<ArrayList<Texture>> spritesgo = new ArrayList<ArrayList<Texture>>();
    int[] listgo = new int[]{7, 7};
    private String[] movementgo = new String[]{"attackgo", "kamego"};
    private static int framego = 0;
    int t = 0;

    public Gohan(int x, int y) {
        this.x = x;
        this.y = y;
        G = new Sprite();
        for (int l = 0; l < listgo.length; l++) {
            for (String h : movementgo) {
                tmpgo = new ArrayList<Texture>();
                for (int v = 0; v < listgo[l]; v++) {
                    tmpgo.add(new Texture("Assets/Sprites/" + h + "/" + h + v + ".png"));
                }
                spritesgo.add(tmpgo);
            }


        }
    }
    public void render (SpriteBatch batch){
        G.draw(batch);

    }
    public int moveframesgo(){
        if(framego < listgo[Main.movego] ){
            if(t < 3) {
                t ++ ;
                if(t == 3) {
                    framego += 1;
                    if (framego == listgo[Main.movego]) {
                        System.out.println(" it is " + battle.turn+"turn");
                        Player.fstat[0] = Player.fstat[0] +(Player.fstat[3]*1/2 - Player.gostat[2]);
                        framego = 0;
                        Main.animation = false;
                        battle.turn = "frieza";
                        Main.movego = 2;
                    }
                    t = 0;
                }
            }
        }

        return framego;
    }
    public void update(SpriteBatch batch, int x, int y) {
        if (Main.animation && Main.movego == Main.Attack) {

            moveframesgo();

        }
        else {
            framego = 0;
        }
        G.set(new Sprite(spritesgo.get(Main.movesv).get(framego)));
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





