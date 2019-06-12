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

    static String Person = "Player", movement = "";
    //                Goku      Vegeta      Gohan      Frieza     Health   Energy  Attack   Defence
    public static final int GOKU = 0, VEGETA = 1, GOHAN = 2, FRIEZA = 3, HP = 0, KI = 1, ATK = 2, DEF = 3;


    public static int turn = GOKU;

    static Rectangle rect;

    static int mx, my;

    private Texture stage;

    private Enemy frieza;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Sprite attack, special;

    public static int frame = 0, type, timer = 0; // type is a integer that determines the move in the array list (ie Attack or special)

    public static int [] t = new int[]{1,2,3,4,5,6}; //this is a counter variable for time

    private static int [] pos = new int[]{710,875,400,875};

    private static int [] def_pos = new int[]{710,875,400,875};

    private int[] list = new int[]{6,7,9}; //list of frames for the moves

    private ArrayList<Integer> Goku_Stat, Vegeta_Stat, Gohan_Stat, Frieza_Stat;

    public ArrayList<ArrayList<Integer>> Stats;

    private boolean Animate;

    public static Music bossbattle;

    public Battle(){

        goku = new Goku();

        vegeta = new Vegeta();

        gohan = new Gohan ();

        frieza = new Enemy();

        stage = new Texture("Assets/Backgrounds/stage.png");

        attack = new Sprite(new Texture("Assets/Fonts/attack.png"));

        special = new Sprite(new Texture("Assets/Fonts/special.png"));

        bossbattle = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/boss.mp3"));

        Goku_Stat = new ArrayList<Integer>(Arrays.asList(8000,300,8000,200));
        Vegeta_Stat = new ArrayList<Integer>(Arrays.asList(7000,500,10000,150));
        Gohan_Stat = new ArrayList<Integer>(Arrays.asList(6500,350,300,300));
        Frieza_Stat = new ArrayList<Integer>(Arrays.asList(50000,500,500,400));

        Stats = new ArrayList<ArrayList<Integer>>(Arrays.asList(Goku_Stat,Vegeta_Stat,Gohan_Stat,Frieza_Stat));

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
            }
        }

        attack.setPosition(x,y);
        special.setPosition(x + 300, y);
        render(batch);
    }


    public int moveFrames(int move, int next, String person, int time, int Enemy){ // takes in the move, whose turn it will be next, player turn or enemy turn, and the time for frames

        if(frame < list[move]){
            if(timer < t[time]) {
                timer ++;
                if(timer == t[time]) {
                    frame += 1;
                    if (frame == list[move]) {
                        System.out.println(" it is " + turn+"turn");
                        updateStats(turn,Enemy);
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

    public void Attack(){ //Attack method
        if(turn == GOKU) {
            pos[0] = (int)(Enemy.F.getX() + 100);
            type = 0;
            moveFrames(1, VEGETA, "Player",3, FRIEZA);
        }
        if(turn == VEGETA){
            pos[3] = (int)(Enemy.F.getX() + 100);
            type = 0;
            moveFrames(1,GOHAN, "Player",3, FRIEZA);
        }
        if(turn == GOHAN){
            pos[1] = (int)(Enemy.F.getX() + 100);
            type = 0;
            moveFrames(1,FRIEZA, "Enemy",3, FRIEZA);
        }
        if(turn == FRIEZA){
            pos[2] = (int)(Goku.Goku.getX() - 100);
            type = 0;
            moveFrames(1,GOKU, "Player",3, GOKU);
        }


    }

    public void Special(){ //Controls all the special moves
        if(turn == GOKU) {
            type = 1;
            moveFrames(2, VEGETA, "Player",3, FRIEZA);
        }
        if(turn == VEGETA){
            type = 1;
            moveFrames(1,GOHAN, "Player",4, FRIEZA);
        }
        if(turn == GOHAN){
            type = 1;
            moveFrames(0,FRIEZA, "Enemy",3, FRIEZA);
        }
        if(turn == FRIEZA){
            type = 0;
            moveFrames(1,GOKU, "Player",3, GOKU);
        }
    }

    public void Defend (){


    }

    public void updateStats(int attacker, int attacked){

        if(movement.equals("Attack")) {
            Stats.get(attacked).set(HP,(Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - Stats.get(attacker).get(ATK))));
            Stats.get(attacker).set(KI,(Stats.get(attacker).get(KI) - 50));
        }

        if(movement.equals("Special")){
            Stats.get(attacked).set(HP,(Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - (int)(Stats.get(attacker).get(ATK)*1.5))));
            Stats.get(attacker).set(KI,(Stats.get(attacker).get(KI) - 50));
        }

    }



}