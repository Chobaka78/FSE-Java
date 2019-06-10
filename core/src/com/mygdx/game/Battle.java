package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Battle {

    static String turn = "goku", Person = "Player", movement = "";

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

    private int[] list = new int[]{7,9}; //list of frames for the moves

    private boolean Animate;

    public Battle(){

        goku = new Goku();

        vegeta = new Vegeta();

        gohan = new Gohan ();

        frieza = new Enemy();

        stage = new Texture("Assets/Backgrounds/stage.png");

        attack = new Sprite(new Texture("Assets/Fonts/attack.png"));

        special = new Sprite(new Texture("Assets/Fonts/special.png"));

    }

    public void render(SpriteBatch batch){
        //Setting original positions
        batch.draw(stage,0,0);
        frieza.update(batch,400,300);
        vegeta.update(batch,875,445);
        gohan.update(batch , 875 , 180);
        goku.update(batch,710,300);
        attack.draw(batch);
        special.draw(batch);

    }

    public void update(SpriteBatch batch, int x, int y){
        mx = Gdx.input.getX();
        my = Math.abs(660 - Gdx.input.getY());
        Main.utils.bossbattle.play();
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

                    }
                    timer = 0;
                }
            }
        }

        return frame; // returns the frame
    }

    public void Attack(){ //Attack method
        if(turn.equals("goku")) {
            type = 0;
            moveFrames(0, "vegeta", "Player",3);
        }
        if(turn.equals("vegeta")){
            type = 0;
            moveFrames(0,"gohan", "Player",3);
        }
        if(turn.equals("gohan")){
            type = 0;
            moveFrames(0,"frieza", "Enemy",3);
        }
        if(turn.equals("frieza")){
            type = 0;
            moveFrames(0,"goku", "Player",3);
        }


    }

    public void Special(){ //Controls all the special moves
        if(turn.equals("goku")) {
            type = 1;
            moveFrames(1, "vegeta", "Player",3);
        }
        if(turn.equals("vegeta")){
            type = 1;
            moveFrames(0,"gohan", "Player",3);
        }
        if(turn.equals("gohan")){
            type = 1;
            moveFrames(0,"frieza", "Enemy",3);
        }
        if(turn.equals("frieza")){
            type = 0;
            moveFrames(0,"goku", "Player",3);
        }
    }


}