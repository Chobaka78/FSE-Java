package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class Enemy {
        static Sprite F;
        private static int x,y;
        static ArrayList<Texture> tmpf;
        static ArrayList<ArrayList<Texture>> spritesf = new ArrayList<ArrayList<Texture>>();
        private static int frame = 0;
        int t =0;

        public Enemy(){
            F = new Sprite();
            for (int l = 0 ; l<7; l++){
                for (String h : new String[]{"Frieza_Attack_Right","specialf"}){
                    tmpf = new ArrayList<Texture>();
                    for (int v = 0; v < 7; v++){
                        tmpf.add(new Texture("Assets/Sprites/Enemy/" + h + "/" + h + v + ".png"));
                    }
                    spritesf.add(tmpf);
                }
            }
        }
        public void render(SpriteBatch batch){
            F.draw(batch);

        }

        public void update(SpriteBatch batch, int x, int y) {
            if(Battle.turn == Battle.FRIEZA){
                F.set(new Sprite(spritesf.get(Battle.type).get(Battle.frame)));
            }
            else{
                F.set(new Sprite(spritesf.get(0).get(0)));
            }

            F.setPosition(x,y);
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
            Enemy.x = x;
        }

    }



