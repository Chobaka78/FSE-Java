package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Battle {

    static String Person = "Player", movement = "";
    //                Goku      Vegeta      Gohan      MINION     Health   Energy  Attack   Defence
    public static final int GOKU = 0, VEGETA = 1, GOHAN = 2, FRIEZA = 3 , MINION = 4, rad = 5, HP = 0, KI = 1, ATK = 2, DEF = 3; // these are assigned values used in the battle class
    public static int turn = GOKU;
    Random random = new Random(); // used for random attacks
    int player;
    ArrayList<String> amountp;

    static Rectangle rect;

    int sx = 705;

    static int mx, my;
    private Texture stage;
    // calling other classes
    private Enemy frieza;
    private Goku goku;
    private Gohan gohan;
    private Vegeta vegeta;
    private Minion minion;
    private Raditz raditz;

    private Sprite attack, special, defend, items; // these are the buttons
    private String item;

    public static boolean enemyalive, onestar, twostar, threestar, fourstar, fivestar, sixstar, sevenstar; // these are boolens that tell program if a dragon ball is obtained

    public static int [] inventory = new int [] {5,5,3}; // inventory

    private ArrayList<String> E ; // enemy list
    private ArrayList<String> P; // plyer list


    public static int frame = 0, type, timer = 0; // type is a integer that determines the move in the array list (ie Attack or special)

    public static int[] t = new int[]{1, 2, 3, 4, 5, 6, 7,8,9,10}; //this is a counter variable for time

    private static int[] pos = new int[]{710, 300, 875, 180, 200, 300, 875, 445};

    private static int[] def_pos = new int[]{710, 300, 875, 180, 200, 300, 875, 445};

    private int[] list = new int[]{6, 7, 8, 9, 1}; //list of frames for the moves

    public static int enemy;

    static Sprite kame;



    private ArrayList<Integer> Goku_Stat, Vegeta_Stat, Gohan_Stat, Frieza_Stat , Minion_Stat,rad_Stat;



    public ArrayList<ArrayList<Integer>> Stats;

    private ArrayList<Integer> DefaultHp;

    private boolean Animate;

    public static Music bossbattle;

    private final int potion = 5;

    private BitmapFont font;

    private Sprite over;

    private String[] Text = new String[]{"Goku","Vegeta","Gohan","Frieza", "Nappa","Radits"};

    public static String g = "alive",v = "alive" ,go = "alive";


    public Battle() {

        //loading all sprites

        goku = new Goku();

        vegeta = new Vegeta();

        gohan = new Gohan();

        frieza= new Enemy();

        minion = new Minion ();

        raditz = new Raditz();

        stage = new Texture("Assets/Backgrounds/stage.png");

        attack = new Sprite(new Texture("Assets/Fonts/attack.png"));

        special = new Sprite(new Texture("Assets/Fonts/special.png"));

        defend = new Sprite(new Texture("Assets/Fonts/defend.png"));

        items = new Sprite(new Texture("Assets/Fonts/items.png"));

        over = new Sprite(new Texture("Assets/Fonts/over.png"));

        kame = new Sprite(new Texture("Assets/Sprites/Goku/kame0.png"));

        bossbattle = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/boss.mp3"));

        E = new ArrayList<String>(Arrays.asList("frieza","minion","raditz"," "));
        P = new ArrayList<String>(Arrays.asList("goku","vegeta","gohan" , " "));

        Goku_Stat = new ArrayList<Integer>(Arrays.asList(8000,150,1800,200));
        Vegeta_Stat = new ArrayList<Integer>(Arrays.asList(7000,250,2200,150));
        Gohan_Stat = new ArrayList<Integer>(Arrays.asList(7500,200,1900,250));
        Frieza_Stat = new ArrayList<Integer>(Arrays.asList(60000,600,3000,400));
        Minion_Stat = new ArrayList<Integer>(Arrays.asList(15000,500,2000,400));
        rad_Stat = new ArrayList<Integer>(Arrays.asList(25000,500,1500,400));

        amountp = new ArrayList<String>(Arrays.asList("0","1","2"));

        DefaultHp = new ArrayList<Integer>(Arrays.asList(8000,7000,7500,50000,15000,1500));
        Stats = new ArrayList<ArrayList<Integer>>(Arrays.asList(Goku_Stat,Vegeta_Stat,Gohan_Stat,Frieza_Stat ,Minion_Stat, rad_Stat));

        font = new BitmapFont();
        font.getData().scale(1f);


    }

    public void render(SpriteBatch batch){
        //Setting original positions
        batch.draw(stage,0,0);

        // this controls whos turn it is
        if (enemy == 3) {
            enemyalive = true;

            frieza.update(batch, pos[4], pos[5]);
        }
        else if (enemy == 4){
            enemyalive = true;
            minion.update(batch,pos[2] , 300);

        }
        else if (enemy == 5){
            enemyalive = true;

            raditz.update(batch,pos[2],300);

        }

        if (Stats.get(4).get(HP) <=0 && E.get(1).equals("minion")){
            Main.EnemyType = 1;
            E.remove("minion");
            onestar = true;
            twostar = true;
            enemyalive = false;
        }


        else if (Stats.get(5).get(HP) <=0 && E.get(1).equals("raditz")){
            E.remove("raditz");
            Main.EnemyType = 2;
            threestar = true;
            fourstar = true;
            enemyalive = false;

        }
        else if (Stats.get(3).get(HP) <=0 && E.get(0).equals("frieza")){
            E.remove("frieza");
            fivestar = true;
            sixstar = true;
            Main.EnemyType = 3;
            enemyalive = false;


        }

        else {
            enemyalive = true;
        }

        if (!enemyalive){ // if the enemy is defeated go back into open world
            bossbattle.stop();
            Main.mode = "open";
            Main.camera.position.x = 275;
        }

        // drawing everyone with their current positions
        vegeta.update(batch,pos[6],pos[7]);
        gohan.update(batch , pos[2] , pos[3]);
        goku.update(batch,pos[0],pos[1]);

        attack.draw(batch);
        if (turn == GOKU && movement.equals("Special") && Animate){
            kame.draw(batch);

        }
        special.draw(batch);

        defend.draw(batch);
        items.draw(batch);
        font.draw(batch, Text[enemy] + " Health: " + Stats.get(enemy).get(HP),2,655);
        font.draw(batch,Text[enemy] + " Ki: " + Stats.get(enemy).get(KI),2,620);

        font.draw(batch,Text[turn] + " Health: " + Stats.get(turn).get(HP),840,655);
        font.draw(batch,Text[turn] + " Ki: " + Stats.get(turn).get(KI), 840,620);

    }

    public void update(SpriteBatch batch, int x, int y){
        mx = Gdx.input.getX();
        my = Math.abs(660 - Gdx.input.getY());
        bossbattle.play();
        rect = new Rectangle(mx,my,1,1); // mouse rect made for collision (1 by 1 square)

        if (Stats.get(0).get(HP) <=0 && P.get(0).equals("goku") ){ // checks if goku health is 0
            P.remove("goku");
            amountp.remove("0");
            turn = VEGETA;
            g = "dead";

        }

        if (Stats.get(1).get(HP) <=0 && P.get(1).equals("vegeta") ){ // checks if vegeta health is 0
            P.remove("vegeta");
            amountp.remove("1");
            turn = GOHAN;
            v = "dead";

        }

        if (Stats.get(2).get(HP) <=0 && P.get(1).equals("gohan") ){ // checks if gohan health is 0
            P.remove("gohan");
            amountp.remove("2");
            turn = enemy;
            go = "dead";

        }

        if (Stats.get(2).get(HP) <=0 && Stats.get(1).get(HP) <=0 && Stats.get(0).get(HP) <=0 || Gdx.input.isKeyPressed(Input.Keys.O) ) // if everyone is dead
        {
            Main.mode = "gameover";
        }


        if(Main.Game.equals("Level1")) { //if the level is 1

            // the following controls all the turns if attack is clicked or defnce special or items
            if (attack.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")) { //this checks for button collision and if its player turn
                movement = "Attack";
                Animate = true; //set the animation to true
            }
            if(special.getBoundingRectangle().overlaps(rect) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Person.equals("Player")){
                sx = 705;
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

        //setting all the positions
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
        if(turn == GOKU && g.equals("alive")) {
            type = 0;
            Goku.defend = false;
            moveFrames(1, VEGETA, "Player",3, enemy );

        }
        else if (g.equals("dead") && turn == GOKU){
            turn = VEGETA;

        }
        else if(turn == VEGETA  && v.equals("alive")){
            //pos[3] = (int)(Enemy.F.getX() + 100);
            type = 0;
            vegeta.d = false ;

            moveFrames(1,GOHAN, "Player",3, enemy);
        }
        else if(v.equals("dead") && turn == VEGETA){
            turn = GOHAN;
        }
        else if(turn == GOHAN && go.equals("alive")){
            //pos[1] = (int)(Enemy.F.getX() + 100);
            type = 0;
            Gohan.defend = false;
            moveFrames(1,enemy, "Enemy",3, enemy);
        }
        else if (go.equals("dead") && turn == GOHAN){
            turn = enemy;
        }
        if(turn == enemy){
            type = 0;
            moveFrames(1,GOKU, "Player",3, player);


        }


    }

    public void Special(){ //Controls all the special moves
        if(turn == GOKU && g == "alive") {
            sx -=15;
            type = 1;
            moveFrames(2, VEGETA, "Player",3, enemy);
        }
        else if (g.equals("dead") && turn == GOKU){
            turn = VEGETA;

        }
        else if(turn == VEGETA && v == "alive"){
            type = 1;
            moveFrames(1,GOHAN, "Player",4, enemy);
        }
        else if (v.equals("dead") && turn == VEGETA){
            turn = GOHAN;

        }

        else if(turn == GOHAN && go == "alive") {
            type = 1;
            moveFrames(0,enemy, "Enemy",3, enemy);
        }
        else if (go.equals("dead") && turn == GOHAN){
            turn = enemy;

        }
        if(turn == enemy  && enemyalive){
            pos[5] +=5;

            System.out.println(pos[5]);
            type =1;
            moveFrames(1,GOKU, "Player",5, player);

        }

        else if (!enemyalive){
            enemyalive = true;
            turn = enemy;
        }


    }

    public void Defend () {
        if (turn == GOKU && g.equals("alive")) {

            moveFrames(4, VEGETA, "Player", 5, enemy);
            Goku.defend = true;
        }
        else if (g.equals("dead") && turn == GOKU){
            turn = VEGETA;

        }
        else if (turn == VEGETA && v.equals("alive")) {

            moveFrames(4, GOHAN, "Player", 5 , enemy);
            Vegeta.d = true;
        }
        else if (v.equals("dead") && turn == VEGETA){
            turn = GOHAN;

        }
        else if (turn == GOHAN && go.equals("alive")) {

            moveFrames(4, enemy, "Enemy", 1 , enemy);
            Gohan.defend = true;

        }

        else if (g.equals("dead") && turn ==GOHAN){
            turn = enemy;

        }
        if (turn == enemy) {
            type = 0;
            moveFrames(1, GOKU , "Player", 3 , player);

        }

    }

    public void Items (){


        if (turn == GOKU && g.equals("alive")) {
            type = 2;
            item = "heal";
            moveFrames(2, VEGETA, "Player", 5, enemy);
        }
        else if (g.equals("dead") && turn == GOKU){
            turn = VEGETA;

        }

        else if (turn == VEGETA && v.equals("alive")) {
            type = 2;
            item = "heal";
            moveFrames(2, GOHAN, "Player", 5, enemy);

        }
        else if (v.equals("dead") && turn == VEGETA){
            turn = GOHAN;

        }
        else if (turn == GOHAN && go.equals("alive")) {
            type = 2;
            item = "heal";
            moveFrames(2, enemy, "Enemy", 5, enemy);

        }
        else if (go.equals("dead") && turn == GOHAN){
            turn = enemy;

        }
        if (turn == enemy) {
            type = 0;
            moveFrames(0, GOKU, "Player", 3, player);

        }
    }



    public void updateStats ( int attacker, int attacked) {
        player = random.nextInt(amountp.size()) + 1;


        if (movement.equals("Attack")) {
            Stats.get(attacked).set(HP, (Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - Stats.get(attacker).get(ATK))));
            Stats.get(attacker).set(KI, (Stats.get(attacker).get(KI) - 50));

        } else if (movement.equals("Special")) {
            Stats.get(attacked).set(HP, (Stats.get(attacked).get(HP) + (Stats.get(attacked).get(DEF) - (int) (Stats.get(attacker).get(ATK) * 2))));
            Stats.get(attacker).set(KI, (Stats.get(attacker).get(KI) - 50));

        } else if (movement.equals("Defend")) {
            Stats.get(attacker).set(DEF, (Stats.get(attacker).get(DEF) + 160));
        } else if (item.equals("heal")) {
            Stats.get(attacker).set(HP, (Stats.get(attacker).get(HP) + 1500));
            inventory[0] = inventory[0] - 1;


        }
    }

    public static int getFrame(){
        return frame;
    }
}



