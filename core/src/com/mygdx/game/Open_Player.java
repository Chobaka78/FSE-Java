package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class Open_Player {
    private static int x, y;
    static Sprite Goku;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    private String [] open_Movement = new String[]{"Up", "Down", "Left", "Right"};
    int [] open_list = new int [] {5,5,5,5};
    public int frames = 0;
    int t = 0;
    static  Box2DDebugRenderer b2dr;
    public Body body;
    Rectangle rect;


    public Open_Player(){
        Goku = new Sprite();
        Load();
        createBody();
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
            for(String w : open_Movement){
                tmp = new ArrayList<Texture>();
                for(int k = 0; k < open_list[i]; k ++){
                    tmp.add(new Texture("Assets/Sprites/" + w + "/" + w + k + ".png"));
                }
                sprites.add(tmp);
            }
        }
    }

    public void createBody(){
        Goku.setPosition(192,175);

        rect = new Rectangle((int) Goku.getX(), (int) Goku.getY(), (int) Goku.getWidth(), (int) Goku.getHeight());

        b2dr = new Box2DDebugRenderer();

        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef1 = new FixtureDef();
        fdef1.shape = shape;
        shape.setAsBox(rect.width * (float) Math.pow(Main.PPM, 2), rect.height * (float) Math.pow(Main.PPM, 2));

        body = Main.world.createBody(bdef2);
        body.createFixture(fdef1).setUserData("Player");
        body.setTransform((float) rect.getX() * Main.PPM, (float) rect.getY() * Main.PPM, 0);


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
        render(batch);
    }
    public void render(SpriteBatch batch){
        batch.draw(Goku,body.getPosition().x - Goku.getWidth() * (float) Math.pow(Main.PPM,2),body.getPosition().y - Goku.getHeight() * (float) Math.pow(Main.PPM, 2), Goku.getWidth() * (float) Math.pow(Main.PPM, 2) * 3, Goku.getHeight() * (float) Math.pow(Main.PPM, 2) * 3);

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
}
