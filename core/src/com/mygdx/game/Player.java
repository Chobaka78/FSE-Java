package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Player {
    static Sprite Goku;
    public static int frame = 0;
    public static final int Punch = 0;
    static int newMove, move;
    private static int x,y;
    private Texture img;
    static ArrayList<Sprite> tmp;
    static ArrayList<ArrayList<Sprite>> sprites = new ArrayList<ArrayList<Sprite>>();
    int [] list = new int []{5,5};
    String [] movement = new String[]{"attackg","kameg"};

    static int[] gstat = {8000, 300, 400, 200}; // hp,ki,attack,def
    static int[] vstat = {7000, 500, 400, 150};
    static int[] gostat = {6500, 350, 300, 300};
    static int[] fstat = {10000, 500, 500, 400};

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        //Goku.set(new Sprite(sprites.get(0).get(0)));
        for(int k = 0; k < list.length; k ++) {
            for (String i : movement) {
                tmp = new ArrayList<Sprite>();
                for (int j = 0; j < list[k]; j++) {
                    tmp.add(new Sprite(new Texture("Sprites/" + i + "/" + i + j + ".png")));
                }
                sprites.add(tmp);

            }
        }


    }


    public void render(SpriteBatch batch){
        //Goku.draw(batch);
    }

    public void update(SpriteBatch batch, ArrayList<ArrayList<Sprite>> sprites){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            newMove = Punch;
        }
        if(move == newMove){
            frame += 1;
            if(frame >= sprites.get(move).size()){
                frame = 1;
            }
            else{
                move = newMove;
                frame = 1;
            }

        }
        render(batch);
    }

}
