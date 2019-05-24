package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class battle {
    static boolean mode = false;
    static int specialm = 3 / 2;
    static String stats = "standby";
    static String turn = "goku";

    public static void battle() {


        //this.mode = mode;
        if (Main.Game.equals("Level1")) {

            Main.utils.bossbattle.play();

            //rect = new Rectangle(mx,my,1,1);
            if (Utils.attacks.getBoundingRectangle().overlaps(Main.rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)&& turn.equals("goku")) {
                System.out.println("goku attack");
                System.out.println(turn);
                stats = "attack";
                System.out.println(Goku.fstat[0]);
                Main.movesg = Main.Attack;
                Main.animation = true;



            }
           if (Utils.attacks.getBoundingRectangle().overlaps(Main.rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && turn.equals("vegeta")){


                System.out.println("vegeta attack");
                stats = "attack";
                Main.movesv = Main.Attack;
                Main.animation = true;
                System.out.println(Goku.fstat[0]);


            }
            //else if (turn.equals("frieza")){
                //Player.gstat[0] =  Player.gstat[0] - Player.fstat[2];
                ///System.out.println("Goku health " + Player.gstat[0]);
                //System.out.println("vegeta health" + Player.vstat[0]);
               // System.out.println(Player.fstat[0]);
                //turn = "";


            //}


        }


        //public static boolean getMode(){
        //  return mode;
        //}

        //public void setMode(boolean mode){
        //  battle.mode= mode;
        //}

    }
}
