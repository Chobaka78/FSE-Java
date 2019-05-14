package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Open_Player {
    private static int x, y;
    public Sprite Goku;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    private String [] open_Movement = new String[]{"Up", "Down", "Left", "Right"};
    int [] open_list = new int [] {5,5,5,5};
    public int frames = 0;
    int t = 0;
    Map map;

    public Open_Player(int x, int y){
            this.x = x;
            this.y = y;
            map = new Map();
            Goku = new Sprite();
        //loading sprites for open
        for(int i = 0; i < open_list.length; i ++ ){
            for(String w : open_Movement){
                tmp = new ArrayList<Texture>();
                for(int k = 0; k < open_list[i]; k ++){
                    tmp.add(new Texture("Assets/Sprites/" + w + "/" + w + k + ".png"));
                }
                sprites.add(tmp);
            }
        }
    }

        public int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < open_list[Main.moves1]){
            if(t < 2) {
                t ++;
                if(t == 2) {
                    frames += 1;
                    if (frames == open_list[Main.moves1]) {
                        frames = 0;
                        Main.animation1 = false;
                    }
                    t = 0;
                }
            }
        }
        return frames;
    }

    public void update(SpriteBatch batch, int x, int y){

        if(Main.animation1 && Main.moves1 == Main.UP){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Down){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Left){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Right){
            moveFrames();
        }
        else{
            frames = 0;
        }


        Goku.set(new Sprite(sprites.get(Main.moves1).get(frames)));
        Goku.setPosition(x,y);
        render(batch);
    }
    public void render(SpriteBatch batch){
        Goku.draw(batch);
    }
}
