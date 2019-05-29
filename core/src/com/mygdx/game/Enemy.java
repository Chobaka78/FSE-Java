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
        int [] listf = new int [] {7,7};
        private String [] movementf = new String[]{"attackf","galick"};
        private static int framef = 0;
        int t =0;
        public Enemy(int x , int y){
            this.x = x;
            this.y = y;
            F = new Sprite();
            for (int l = 0 ; l<listf.length; l++){
                for (String h : movementf){
                    tmpf = new ArrayList<Texture>();
                    for (int v = 0; v<listf[l]; v++){
                        tmpf.add(new Texture("Assets/Sprites/" + h + "/" + h+ v + ".png"));
                    }
                    spritesf.add(tmpf);
                }
            }
        }
        public void render(SpriteBatch batch){
            F.draw(batch);

        }

        public int moveFramesf(){ // this is the animation for the movement of the character
            if(framef < listf[Main.movef] ){
                if(t < 3) {
                    t ++ ;
                    if(t == 3) {
                        framef += 1;
                        if (framef == listf[Main.movef]) {
                            System.out.println(" it is " + battle.turn+"turn");
                            Goku.gstat[0] = Goku.gstat[0] + (Goku.gstat[3]*1/3 - Goku.fstat[2]);
                            framef = 0;
                            Main.animation = false;
                            battle.turn = "goku";;
                            Main.movef = 2;
                        }
                        t = 0;
                    }
                }
            }

            return framef;
        }


        public int moveStance(){ // this is the animation for the stance of the character
            if(framef < 3) {
                if (t < 3) {
                    t++;
                    if (t == 3) {
                        framef += 1;
                        if (framef == 3) {
                            framef = 0;
                            Main.animation = false;
                        }
                        t = 0;
                    }
                }
            }
            return framef;
        }
        public void update(SpriteBatch batch, int x, int y) {
            if (Main.animation && Main.movef == Main.Attack) {


                moveFramesf();

            }
            else {
                framef = 0;
            }
            F.set(new Sprite(spritesf.get(Main.movef).get(framef)));
            F.setPosition(200,300);
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



