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
    public Texture def;
    static boolean defend;

    public Gohan(){

        G = new Sprite();
        def = new Texture("Assets/Sprites/Gohan/defendgo/defendgo0.png");


        for (int l = 0; l < listgo.length; l++) {
            for (String h : movementgo) {
                tmpgo = new ArrayList<Texture>();
                for (int v = 0; v < listgo[l]; v++) {
                    tmpgo.add(new Texture("Assets/Sprites/Gohan/" + h + "/" + h + v + ".png"));
                }
                spritesgo.add(tmpgo);
            }




        }
        for (String h : new String[]{"gohanheal"}){
            tmpgo = new ArrayList<Texture>();
            for (int v = 0; v < 8 ; v++){
                tmpgo.add(new Texture("Assets/Sprites/Gohan/" + h + "/" + h+ v + ".png"));
            }
            spritesgo.add(tmpgo);
        }



    }
    public void render (SpriteBatch batch){
        G.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y) {
        if(Battle.turn == Battle.GOHAN &&  Battle.Person.equals("Player" )) {
            defend = false;
            G.set(new Sprite(spritesgo.get(Battle.type).get(Battle.frame)));
        }
        else{
            G.set(new Sprite(spritesgo.get(0).get(0)));
        }
        if (defend){
            G.set(new Sprite(def));
        }

        G.setPosition(x,y);
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





