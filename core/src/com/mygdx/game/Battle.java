package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle {

    static String turn = "goku", Person = "Player", movement = "";

    static Rectangle rect;

    static int mx, my;

    private Texture stage;

    private Enemy frieza;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Sprite attack, special , defend;

    public static int frame = 0, type, timer = 0; // type is a integer that determines the move in the array list (ie Attack or special)

    public static int [] t = new int[]{1,2,3,4,5,6,100}; //this is a counter variable for time

    private static int [] pos = new int[]{710,875,400,875};

    private static int [] def_pos = new int[]{710,875,400,875};

    private int[] list = new int[]{6,7,9,1}; //list of frames for the moves

    private ArrayList<Integer> Goku_Stat, Vegeta_Stat, Gohan_Stat, Frieza_Stat;

    private boolean Animate;

    public static Music bossbattle;

    public static String[] split;

    public Battle(){

        goku = new Goku();

        vegeta = new Vegeta();

        gohan = new Gohan ();

        frieza = new Enemy();

        String mode = " ";

        stage = new Texture("Assets/Backgrounds/stage.png");

        attack = new Sprite(new Texture("Assets/Fonts/attack.png"));

        special = new Sprite(new Texture("Assets/Fonts/special.png"));

        defend = new Sprite(new Texture("Assets/Fonts/preview.png"));



        bossbattle = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/boss.mp3"));

        Stats();

    }

    public void render(SpriteBatch batch){
        //Setting original positions
        batch.draw(stage,0,0);
        frieza.update(batch,pos[2],300);
        vegeta.update(batch,pos[3],445);
        gohan.update(batch , pos[1] , 180);
        goku.update(batch,pos[0],300);
        attack.draw(batch);
        special.draw(batch);
        defend.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y){
        mx = Gdx.input.getX();
        my = Math.abs(660 - Gdx.input.getY());
        bossbattle.play();
        rect = new Rectangle(mx,my,1,1); // mouse rect made for collision (1 by 1 square)

        if(Main.Game.equals("Level1")) {
            if (attack.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")) { //this checks for button collision and if its player turn
                movement = "Attack";
                Animate = true; //set the animation to true
            }
            if(special.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")){
                movement = "Special";
                Animate = true;
            }

            if(defend.getBoundingRectangle().overlaps(rect) && Gdx.input.isKeyPressed(Input.Keys.E) && Person.equals("Player")){
                movement = "Defend";
                Animate = true;

            }
            else if(Person.equals("Enemy")){
                Animate = true;
            }


            if(Animate){
                if(movement.equals("Attack")) {
                    Attack();
                }
                else if(movement.equals("Special")){
                    Special();
                }
                else if (movement.equals("Defend")){

                    Defend();
                }
            }
        }

        attack.setPosition(x,y);
        special.setPosition(x + 300, y);
        defend.setPosition(x+60,y);
        render(batch);
    }


    public int moveFrames(int move, String next, String person, int time){ // takes in the move, whose turn it will be next, player turn or enemy turn, and the time for frames
        if(frame < list[move]){
            if(timer < t[time]) {
                timer ++;
                if(timer == t[time]) {
                    frame += 1;
                    if (frame == list[move]) {
                        System.out.println(" it is " + Battle.turn+"turn");
                        turn = next;
                        Person = person;
                        Animate = false;
                        frame = 0;
                        for(int i = 0; i < pos.length; i ++){
                            pos[i] = def_pos[i];
                        }

                    }
                    timer = 0;
                }
            }
        }

        return frame; // returns the frame
    }


    public void Attack() { //Attack method
            if (turn.equals("goku")) {
                pos[0] = (int) (Enemy.F.getX() + 100);
                type = 0;
                moveFrames(1, "vegeta", "Player", 3);
            }
            if (turn.equals("vegeta")) {
                pos[3] = (int) (Enemy.F.getX() + 100);
                type = 0;
                moveFrames(1, "gohan", "Player", 3);
            }
            if (turn.equals("gohan")) {
                pos[1] = (int) (Enemy.F.getX() + 100);
                type = 0;
                moveFrames(1, "frieza", "Enemy", 3);
            }
            if (turn.equals("frieza")) {
                pos[2] = (int) (Goku.Goku.getX() - 100);
                type = 0;
                moveFrames(1, "goku", "Player", 3);

            }


        }
    }

    public void Special(){ //Controls all the special moves
        if(turn.equals("goku")) {
            type = 1;
            moveFrames(2, "vegeta", "Player",3);
        }
        if(turn.equals("vegeta")){
            type = 1;
            moveFrames(1,"gohan", "Player",4);
        }
        if(turn.equals("gohan")){
            type = 1;
            moveFrames(0,"frieza", "Enemy",3);
        }
        if(turn.equals("frieza")){
            type = 0;
            moveFrames(1,"goku", "Player",3);
        }
    }

    public void Defend (){
        if (turn.equals("goku")){
            type = 2;

            moveFrames(3, "vegeta", "Player",3);

        }
        if (turn.equals("vegeta")){
            type = 2;
            moveFrames(3,"gohan" , "Player" , 3);
        }
        if (turn.equals("gohan")){
            type = 2;
            moveFrames(3 , "frieza" , "Player" , 3);
        }
        if (turn.equals("frieza")){
            type = 2;
            moveFrames(3  , "goku" , "Player" , 3);


        }


    }

    public void Stats() {
        Goku_Stat = new ArrayList<Integer>(Arrays.asList(8000,300,8000,200));



    }


}