package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Vegeta {
    static Sprite V;
    private static int x,y;
    static ArrayList<Texture> tmpv;
    static ArrayList<ArrayList<Texture>> spritesv = new ArrayList<ArrayList<Texture>>();
    int [] listv = new int [] {7,7};
    private String [] movementv = new String[]{"attackv","galick"};
    private static int framev = 0;
    int t =0;

    public Vegeta(int x , int y){
        this.x = x;
        this.y = y;
        V = new Sprite();
        for (int l = 0 ; l<listv.length; l++){
            for (String h : movementv){
                tmpv = new ArrayList<Texture>();
                for (int v = 0; v<listv[l]; v++){
                    tmpv.add(new Texture("Assets/Sprites/" + h + "/" + h+ v + ".png"));
                }
                spritesv.add(tmpv);
            }
        }
    }
    public void render(SpriteBatch batch){
        V.draw(batch);
    }

    public int moveFramesv(){ // this is the animation for the movement of the character
        if(framev < listv[Main.movesv] ){
            if(t < 3) {
                t ++ ;
                if(t == 3) {
                    framev += 1;
                    if (framev == listv[Main.movesv]) {
                        System.out.println(" it is " + battle.turn+"turn");

                        Player.fstat[0] = Player.fstat[0] + (Player.fstat[3]*1/2 - Player.vstat[2]);
                        framev = 0;
                        System.out.println("frieza health : " + Player.fstat[0]);
                        Main.animation = false;
                        battle.turn = "gohan";
                        Main.movesv = 2;
                    }
                    t = 0;
                }
            }
        }

        return framev;
    }


    public int moveStance(){ // this is the animation for the stance of the character
        if(framev < 3) {
            if (t < 3) {
                t++;
                if (t == 3) {
                    framev += 1;
                    if (framev == 3) {
                        framev = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return framev;
    }
    public void update(SpriteBatch batch, int x, int y) {
        if (Main.animation && Main.movesv == Main.Attack) {

            moveFramesv();

        }
        else {
            framev = 0;
        }
        V.set(new Sprite(spritesv.get(Main.movesv).get(framev)));
        V.setPosition(700,300);
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
        Vegeta.x = x;
    }

}
