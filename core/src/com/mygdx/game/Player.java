package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Player {
    static Sprite Goku;
    private static int x,y;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    int [] list = new int []{7,7};
    String [] movement = new String[]{"attackg","kameg"};
    private static int frame = 0;
    int t = 0;

    static int[] gstat = {8000, 300, 400, 200}; // hp,ki,attack,def
    static int[] vstat = {7000, 500, 400, 150};
    static int[] gostat = {6500, 350, 300, 300};
    static int[] fstat = {10000, 500, 500, 400};

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        Goku = new Sprite();

        for(int k = 0; k < list.length; k ++) {
            for (String i : movement) {
                tmp = new ArrayList<Texture>();
                for (int j = 0; j < list[k]; j++) {
                    tmp.add(new Texture("Assets/Sprites/" + i + "/" + i + j + ".png"));
                }
                sprites.add(tmp);
            }
        }
    }
    public void render(SpriteBatch batch){
        Goku.draw(batch);
    }

    public int moveFrames(){ // this is the animation for the movement of the character
        if(frame < list[Main.moves]){
            if(t < 3) {
                t ++;
                if(t == 3) {
                    frame += 1;
                    if (frame == list[Main.moves]) {
                        Player.fstat[0] = Player.fstat[0] - Player.gstat[2];
                        frame = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return frame;
    }

    public int moveStance(){ // this is the animation for the stance of the character
        if(frame < 3) {
            if (t < 3) {
                t++;
                if (t == 3) {
                    frame += 1;
                    if (frame == 3) {
                        frame = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return frame;
    }

    public void update(SpriteBatch batch){
        if(Main.animation && Main.moves == Main.Attack){
            moveFrames();
        }
        else if(Main.animation && Main.moves == Main.Kick){
            moveFrames();
        }
        else{
            moveStance();
        }


        System.out.println(t + "," + frame);
        Goku.set(new Sprite(sprites.get(Main.moves).get(frame)));
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
        Player.x = x;
    }

}
