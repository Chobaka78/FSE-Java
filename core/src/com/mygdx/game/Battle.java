package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle {

    static String Person = "Player", movement = "";
    //                Goku      Vegeta      Gohan      MINION     Health   Energy  Attack   Defence
    public static final int GOKU = 0, VEGETA = 1, GOHAN = 2, FRIEZA = 3 , MINION = 4, HP = 0, KI = 1, ATK = 2, DEF = 3;


    public static int turn = GOKU;

    static Rectangle rect;

    int sx = 705;

    static int mx, my;

    private Texture stage;

    private Enemy frieza;

    private Goku goku;

    private Gohan gohan;

    private Vegeta vegeta;

    private Minion minion;

    private Sprite attack, special, defend, items;

    private String item;

    private Sprite over;



    public static int frame = 0, type, timer = 0; // type is a integer that determines the move in the array list (ie Attack or special)

    public static int[] t = new int[]{1, 2, 3, 4, 5, 6, 100}; //this is a counter variable for time

    private static int[] pos = new int[]{710, 875, 400, 875};

    private static int[] def_pos = new int[]{710, 875, 400, 875};

    private int[] list = new int[]{6, 7, 8, 9, 1}; //list of frames for the moves

    public static int enemy;

    static Sprite kame;


    private ArrayList<Integer> Goku_Stat, Vegeta_Stat, Gohan_Stat, Frieza_Stat , Minion_Stat;

    public ArrayList<ArrayList<Integer>> Stats;

    private boolean Animate;

    public static Music bossbattle;

    private final int potion = 5;

    public Battle() {

        goku = new Goku();

        vegeta = new Vegeta();

        gohan = new Gohan();

        frieza= new Enemy();

        minion = new Minion ();

        stage = new Texture("Assets/Backgrounds/stage.png");

        attack = new Sprite(new Texture("Assets/Fonts/attack.png"));

        special = new Sprite(new Texture("Assets/Fonts/special.png"));

        defend = new Sprite(new Texture("Assets/Fonts/defend.png"));

        items = new Sprite(new Texture("Assets/Fonts/items.png"));

        over = new Sprite(new Texture("Assets/Fonts/over.png"));

        kame = new Sprite(new Texture("Assets/Sprites/Goku/kame0.png"));


        bossbattle = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/boss.mp3"));

        Goku_Stat = new ArrayList<Integer>(Arrays.asList(8000,300,1800,200));
        Vegeta_Stat = new ArrayList<Integer>(Arrays.asList(7000,500,2200,150));
        Gohan_Stat = new ArrayList<Integer>(Arrays.asList(7500,400,1900,300));
        Frieza_Stat = new ArrayList<Integer>(Arrays.asList(50000,500,500,400));
        Minion_Stat = new ArrayList<Integer>(Arrays.asList(15000,500,500,400));

        Stats = new ArrayList<ArrayList<Integer>>(Arrays.asList(Goku_Stat,Vegeta_Stat,Gohan_Stat,Frieza_Stat,Minion_Stat));

    }

    public void render(SpriteBatch batch){
        //Setting original positions
        batch.draw(stage,0,0);
        if (enemy == 3) {

            frieza.update(batch, pos[2], 300);
        }
        else if (enemy == 4){
            minion.update(batch,pos[2] , 300);

        }

        if (Stats.get(4).get(HP) <=0 || Frieza_Stat.get(0) <=0){
            Battle.bossbattle.stop();
            Main.mode = "open";
            Stats.get(4).set(HP,5000);
        }
        vegeta.update(batch,pos[3],445);
        gohan.update(batch , pos[1] , 180);
        goku.update(batch,pos[0],300);

        attack.draw(batch);
        if (turn == GOKU && movement.equals("Special") && Animate){
            kame.draw(batch);

        }
        special.draw(batch);

        defend.draw(batch);
        items.draw(batch);

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
                sx = 705;
                System.out.println("special mode");
                movement = "Special";
                Animate = true;
            }

            if(defend.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")){
                movement = "Defend";
                Animate = true;

            }

            if (items.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")){
                movement = "item";
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
                else if (movement.equals("item")){
                    Items();
                }
            }
        }

        attack.setPosition(x-100,y);
        special.setPosition(x + 200, y);
        defend.setPosition(x+ 500,y);
        items.setPosition(x + 800 , y);
        kame.setPosition(sx,350);
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

            type = 0;
            Goku.defend = false;
            moveFrames(1, VEGETA, "Player",3, enemy );

        }
        if(turn == VEGETA ){
            //pos[3] = (int)(Enemy.F.getX() + 100);
            type = 0;
            vegeta.d = false ;

            moveFrames(1,GOHAN, "Player",3, enemy);
        }
        if(turn == GOHAN){
            //pos[1] = (int)(Enemy.F.getX() + 100);
            type = 0;
            Gohan.defend = false;
            moveFrames(1,enemy, "Enemy",3, enemy);
        }
        if(turn == enemy){
            System.out.println("K");
            pos[2] = (int)(Goku.Goku.getX() - 100);
            type = 0;

            moveFrames(0,GOKU, "Player",3, GOKU);


        }


    }

    public void Special(){ //Controls all the special moves
        if(turn == GOKU) {

            sx -=15;
            type = 1;
            moveFrames(2, VEGETA, "Player",3, enemy);
        }
        if(turn == VEGETA){
            type = 1;
            moveFrames(1,GOHAN, "Player",4, enemy);
        }
        if(turn == GOHAN){
            type = 1;
            moveFrames(0,enemy, "Enemy",3, enemy);
        }
        if(turn == enemy){
            type =0;
            moveFrames(0,GOKU, "Player",3, GOKU);

        }
    }

    public void Defend () {
        if (turn == GOKU) {
            System.out.println("goku defend222");
            moveFrames(4, VEGETA, "Player", 5, enemy);
            Goku.defend = true;
        }
        else if (turn == VEGETA ) {

            System.out.println("vegeta def");
            moveFrames(4, GOHAN, "Player", 5 , enemy);
            Vegeta.d = true;
        }
        else if (turn == GOHAN) {


            System.out.println("gohan defend");
            moveFrames(4, enemy, "Enemy", 1 , enemy);
            Gohan.defend = true;

        }
        if (turn == enemy) {
            type = 0;
            System.out.println("turn freiza ");
            moveFrames(0, GOKU , "Player", 3 , enemy);

        }

    }

    public void Items (){


            if (turn == GOKU) {
                type = 2;
                System.out.println("heal");
                item = "heal";
                moveFrames(2, VEGETA, "Player", 5, enemy);
            } else if (turn == VEGETA) {
                type = 2;
                System.out.println("vegeta def");
                item = "heal";
                moveFrames(2, GOHAN, "Player", 5, enemy);

            } else if (turn == GOHAN) {
                type = 4;
                System.out.println("gohan defend");
                item = "heal";
                moveFrames(2, enemy, "Enemy", 5, enemy);

            }
            if (turn == enemy) {
                type = 0;
                //System.out.println("turn freiza ");
                moveFrames(0, GOKU, "Player", 3, enemy);

            }
        }



        public void updateStats ( int attacker, int attacked){

            if (movement.equals("Attack")) {
                Stats.get(attacked).set(HP, (Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - Stats.get(attacker).get(ATK))));
                Stats.get(attacker).set(KI, (Stats.get(attacker).get(KI) - 50));
                System.out.println(Minion_Stat);
                System.out.println(Goku_Stat);
            }

            else if (movement.equals("Special")) {
                Stats.get(attacked).set(HP, (Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - (int) (Stats.get(attacker).get(ATK) * 6))));
                Stats.get(attacker).set(KI, (Stats.get(attacker).get(KI) - 50));
            }

            //else if (item.equals("heal")&& potion>0){
                //Stats.get(attacker).set(HP, (Stats.get(attacker).get(HP)+1500));
                //System.out.println(Goku_Stat);
              //  System.out.println(Vegeta_Stat);

            //}
            //else if (item.equals("kiregen")){
              //  Stats.get(attacker).set(KI, (Stats.get(attacker).get(KI)+150));
                //System.out.println(Goku_Stat);
                //System.out.println(Vegeta_Stat);


            //}

        }
    }



