

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class Minion {
    static Sprite M;
    private static int x,y;
    static ArrayList<Texture> tmpm;
    static ArrayList<ArrayList<Texture>> spritesm = new ArrayList<ArrayList<Texture>>();

    public Minion(){
        M = new Sprite();
        for (int l = 0 ; l<7; l++){
            for (String h : new String[]{"minionatk"}){
                tmpm = new ArrayList<Texture>();
                for (int v = 0; v < 7; v++){
                    tmpm.add(new Texture("Assets/Sprites/Minion/" + h + "/" + h + v + ".png"));
                }
                spritesm.add(tmpm);
            }
        }

    }
    public void render(SpriteBatch batch){
        M.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y) {
        if(Battle.turn == Battle.MINION){
            M.set(new Sprite(spritesm.get(Battle.type).get(Battle.frame)));
        }
        else{
            M.set(new Sprite(spritesm.get(0).get(0)));
        }

        M.setPosition(200,300);
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
        Minion.x = x;
    }

}



