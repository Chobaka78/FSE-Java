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
    static Texture def;
   static boolean d ;


    public Vegeta(){

        V = new Sprite();
        def = new Texture("Assets/Sprites/Vegeta/defendv/defendv0.png");

            for (String h : new String[]{"attackv", "Special"}){
                tmpv = new ArrayList<Texture>();
                for (int v = 0; v < 7; v++){
                    tmpv.add(new Texture("Assets/Sprites/Vegeta/" + h + "/" + h+ v + ".png"));
                }
                spritesv.add(tmpv);
            }

        for (String h : new String[]{"vegetaheal"}){
            tmpv = new ArrayList<Texture>();
            for (int v = 0; v < 8 ; v++){
                tmpv.add(new Texture("Assets/Sprites/Vegeta/" + h + "/" + h+ v + ".png"));
            }
            spritesv.add(tmpv);
        }





    }
    public void render(SpriteBatch batch){
        V.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y) {
        if(Battle.turn == Battle.VEGETA &&  Battle.Person.equals("Player" )) {
            System.out.println("ZZZZAAAAAAAIIIIIN SHUT UP");
            d= false;
            V.set(new Sprite(spritesv.get(Battle.type).get(Battle.frame)));
        }
        else{
            V.set(new Sprite(spritesv.get(0).get(0)));
        }
        if(d){
            V.set(new Sprite(def));
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
