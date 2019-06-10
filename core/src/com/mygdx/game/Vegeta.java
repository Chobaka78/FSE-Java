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

    public Vegeta(){

        V = new Sprite();

            for (String h : new String[]{"attackv", "Special"}){
                tmpv = new ArrayList<Texture>();
                for (int v = 0; v < 7; v++){
                    tmpv.add(new Texture("Assets/Sprites/Vegeta/" + h + "/" + h+ v + ".png"));
                }
                spritesv.add(tmpv);
            }


    }
    public void render(SpriteBatch batch){
        V.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y) {
        if(Battle.turn.equals("vegeta")) {
            V.set(new Sprite(spritesv.get(Battle.type).get(Battle.frame)));
        }
        else{
            V.set(new Sprite(spritesv.get(0).get(0)));
        }

        V.setPosition(x,y);
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
