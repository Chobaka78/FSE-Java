package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class OpenEnemy {
    private static int x, y;

    static Sprite Frieza, Nappa, Raditz;

    static ArrayList<Texture> tmp, tmp1,tmp2;

    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();

    public int frames = 0;

    int[] moves = new int[] {3,5,9};

    int t = 0;

    public static int[] timer = new int[]{1, 2, 3, 4, 5, 6, 7,8,9,10}; //this is a counter variable for time




    Body body, body1, body2;
    Rectangle rect, rect1, rect2;

    public OpenEnemy(){
        Nappa = new Sprite();
        Raditz = new Sprite();
        Frieza = new Sprite();
        createNappaBody();
        createRaditzBody();
        createBody();

        Load();
    }

    public void Load(){
        tmp = new ArrayList<Texture>();
        for (int j = 0; j < 4; j++) {
            tmp.add(new Texture("Assets/Sprites/openEnemy/Nappa_Stance_Left/Nappa_Stance_Left" + j + ".png"));
        }
        sprites.add(tmp);

        tmp1 = new ArrayList<Texture>();
        for (int j = 0; j < 6; j++) {
            tmp1.add(new Texture("Assets/Sprites/openEnemy/Raditz_Stance_Left/Raditz_Stance_Left" + j + ".png"));
        }
        sprites.add(tmp1);

        tmp2 = new ArrayList<Texture>();
        for (int j = 0; j < 10; j++) {
            tmp2.add(new Texture("Assets/Sprites/openEnemy/Frieza_Stance_Left/Frieza_Stance_Left" + j + ".png"));
        }
        sprites.add(tmp2);

    }

    public void update(SpriteBatch batch){

        if(Main.EnemyType == 0){
            body1.setTransform(Nappa.getX(), Nappa.getY(),0);
            body2.setTransform(3000, 3000,0);
            body.setTransform(3000, 3000,0);
        }

        if(Main.EnemyType == 1){
            body2.setTransform(Raditz.getX(), Raditz.getY(),0);
            body1.setTransform(3000, 3000,0);
            body.setTransform(3000, 3000,0);
        }

        if(Main.EnemyType == 2){
            body.setTransform(550, 91,0);
            body2.setTransform(3000, 3000,0);
            body1.setTransform(3000, 3000,0);
        }

        if(Main.EnemyType == 3){
            body.setTransform(3000, 3000,0);
            body2.setTransform(3000, 3000,0);
            body1.setTransform(3000, 3000,0);
        }

        if(Main.EnemyType == 0){
            Nappa.set(new Sprite(sprites.get(Main.EnemyType).get(frames)));
            Nappa.setPosition(body1.getPosition().x, body1.getPosition().y);
            moveFrames(Main.EnemyType,5);
        }

        if(Main.EnemyType == 1){
            Raditz.set(new Sprite(sprites.get(Main.EnemyType).get(frames)));
            Raditz.setPosition(body2.getPosition().x, body2.getPosition().y);
            moveFrames(Main.EnemyType,4);
        }

        if(Main.EnemyType == 2) {
            Frieza.set(new Sprite(sprites.get(Main.EnemyType).get(frames)));
            Frieza.setPosition(body.getPosition().x, body.getPosition().y);
            moveFrames(Main.EnemyType,3);
        }

        render(batch, Main.EnemyType);
    }

    public void render(SpriteBatch batch, int choice){
        if(choice == 0){
            batch.draw(Nappa, body1.getPosition().x - 8, body1.getPosition().y - 5, Nappa.getWidth() * (float) Math.pow(Main.PPM, 2), Nappa.getHeight() * (float) Math.pow(Main.PPM, 2));

        }

        if(choice == 1){
            batch.draw(Raditz, body2.getPosition().x - 3, body2.getPosition().y - 8, Raditz.getWidth() * (float) Math.pow(Main.PPM, 2) * 3 / 2, Raditz.getHeight() * (float) Math.pow(Main.PPM, 2) * 3 / 2);

        }
        if(choice == 2) {
            batch.draw(Frieza, body.getPosition().x - 3, body.getPosition().y - 8, Frieza.getWidth() * (float) Math.pow(Main.PPM, 2) * 3 / 2, Frieza.getHeight() * (float) Math.pow(Main.PPM, 2) * 3 / 2);
        }
    }

    public int moveFrames(int choice, int time){ // this is the animation for the movement frames the character
        if(frames < moves[choice]){
            if(t < timer[time]) {
                t ++;
                if(t == timer[time]) {
                    frames += 1;
                    if (frames == moves[choice]) {
                        frames = 0;
                        Main.animation = false;
                    }
                    t = 0;
                }
            }
        }
        return frames;
    }

    public void createBody(){
        Frieza.setPosition(544,91);

        rect = new Rectangle((int)Frieza.getX(), (int)Frieza.getY(), (int)Frieza.getWidth(), (int)Frieza.getHeight());

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        body = Main.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;

        shape.setAsBox(48* (float) Math.pow(Main.PPM, 2), 88* (float) Math.pow(Main.PPM, 2));

        body.createFixture(fdef).setUserData("Frieza");


        body.setTransform((float) rect.getX(), (float) rect.getY(), 0);


    }

    public void createNappaBody(){
        Nappa.setPosition(160,105);

        rect1 = new Rectangle((int)Nappa.getX(), (int)Nappa.getY(), (int)Nappa.getWidth(), (int)Nappa.getHeight());

        BodyDef bdef1 = new BodyDef();
        bdef1.type = BodyDef.BodyType.StaticBody;
        body1 = Main.world.createBody(bdef1);

        FixtureDef fdef1 = new FixtureDef();
        PolygonShape shape1 = new PolygonShape();

        fdef1.shape = shape1;

        shape1.setAsBox(55f* (float) Math.pow(Main.PPM, 2), 60f* (float) Math.pow(Main.PPM, 2));

        body1.createFixture(fdef1).setUserData("Nappa");


        body1.setTransform((float) rect1.getX(), (float) rect1.getY(), 0);


    }

    public void createRaditzBody(){
        Raditz.setPosition(318,100);

        rect2 = new Rectangle((int)Raditz.getX(), (int)Raditz.getY(), (int)Raditz.getWidth(), (int)Raditz.getHeight());

        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.StaticBody;
        body2 = Main.world.createBody(bdef2);

        FixtureDef fdef2 = new FixtureDef();
        PolygonShape shape2 = new PolygonShape();

        fdef2.shape = shape2;

        shape2.setAsBox(53f* (float) Math.pow(Main.PPM, 2), 71* (float) Math.pow(Main.PPM, 2));

        body2.createFixture(fdef2).setUserData("Raditz");


        body2.setTransform((float) rect2.getX(), (float) rect2.getY(), 0);


    }

}