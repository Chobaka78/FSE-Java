package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private static int x, y;
    static Sprite Goku;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    int [] open_list = new int [] {5,5,5,5};
    public int frames = 0;
    int t = 0;
    Body body;
    Rectangle rect;
    int width = 18, height = 34;


    public Player(){
        Goku = new Sprite();
        createBody();
        Load();
    }

    public void render(SpriteBatch batch){
        batch.draw(Goku,body.getPosition().x - width/2, body.getPosition().y - height/2);

    }

    public int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < open_list[Main.moves1]){
            if(t < 2) {
                t ++;
                if(t == 2) {
                    frames += 1;
                    if (frames == open_list[Main.moves1]) {
                        frames = 0;
                        Main.animation1 = false;
                    }
                    t = 0;
                }
            }
        }
        return frames;
    }

    public void Load(){
        for(int i = 0; i < open_list.length; i ++ ){
            for(String w : new String[]{"Up", "Down", "Left", "Right"}){
                tmp = new ArrayList<Texture>();
                for(int k = 0; k < open_list[i]; k ++){
                    tmp.add(new Texture("Assets/Sprites/" + w + "/" + w + k + ".png"));
                }
                sprites.add(tmp);
            }
        }
    }

    public void createBody(){

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.body = Main.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;

        shape.setAsBox(5,10);

        this.body.createFixture(fdef);

        this.body.getFixtureList().get(0).setUserData("Player");
        this.body.getFixtureList().get(0).setUserData("Player");

        this.body.setTransform(192,175, 0);


    }

    public void update(SpriteBatch batch){

        Goku.setPosition(body.getPosition().x,body.getPosition().y);

        if(Main.animation1 && Main.moves1 == Main.UP){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Down){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Left){
            moveFrames();
        }
        else if(Main.animation1 && Main.moves1 == Main.Right){
            moveFrames();
        }
        else{
            frames = 0;
        }

        Goku.set(new Sprite(sprites.get(Main.moves1).get(frames)));
        this.render(batch);
    }

    public void setX(float x) {
        Goku.setX(x);
    }

    public void setY(float y) {
        Goku.setY(y);
    }

    public float getX() {
        return Goku.getX();
    }

    public float getY() {
        return Goku.getY();
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Body getBody() {
        return body;
    }
}
