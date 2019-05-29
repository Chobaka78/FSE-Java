package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class battle {
    static boolean mode = false;
    static int specialm = 3 / 2;
    static String stats = "standby";
    static String turn = "goku";
    static Rectangle r;

    public static void battle() {
        //this.mode = mode;
        r  = new Rectangle(500,200,1,1);
        if (Main.Game.equals("Level1")) {

            Main.utils.bossbattle.play();
            //rect = new Rectangle(mx,my,1,1);
            if (Utils.attacks.getBoundingRectangle().overlaps(r) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)&& turn.equals("goku")) {


                Main.movesg = Main.Attack;
                Main.animation = true;
            }
           if (Utils.attacks.getBoundingRectangle().overlaps(r) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && turn.equals("vegeta")){

                Main.movesv = Main.Attack;
                Main.animation = true;

            }

            if (Utils.attacks.getBoundingRectangle().overlaps(r) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && turn.equals("gohan")){
                Main.movego = Main.Attack;
                Main.animation = true;

            }
           if (turn.equals("frieza")){
                Main.movef = Main.Attack;
                Main.animation = true;
            }


        }


        //public static boolean getMode(){
        //  return mode;
        //}

        //public void setMode(boolean mode){
        //  battle.mode= mode;
        //}

    }
}
