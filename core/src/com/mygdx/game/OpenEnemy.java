package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class OpenEnemy {
    private static int x, y;
    static Sprite Frieza;
    static ArrayList<Texture> tmp;
    static ArrayList<ArrayList<Texture>> sprites = new ArrayList<ArrayList<Texture>>();
    public int frames = 0;
    private String [] movement = new String[] {"Frieza"};
    private int [] list = new int[]{10};

    int t = 0;
    Body body;
    Rectangle rect;

    public OpenEnemy(){
        Frieza = new Sprite();
        createBody();
        Load();
    }

    public void Load(){
        for(int k = 0; k < 1; k ++) {
            tmp = new ArrayList<Texture>();
            for (int j = 0; j < list[k]; j++) {
                tmp.add(new Texture("Assets/Sprites/openEnemy/Frieza_Stance_Left/Frieza_Stance_Left" + j + ".png"));
            }
            sprites.add(tmp);
        }
    }

    public void update(SpriteBatch batch){

        Frieza.setPosition(body.getPosition().x,body.getPosition().y);
        moveFrames();

        Frieza.set(new Sprite(sprites.get(0).get(frames)));
        render(batch);
    }

    public void render(SpriteBatch batch){
        batch.draw(Frieza,body.getPosition().x - 3 ,body.getPosition().y - 10, Frieza.getWidth() * (float) Math.pow(Main.PPM, 2) * 3/2, Frieza.getHeight() * (float) Math.pow(Main.PPM, 2) * 3/2);

    }

    public int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < 9){
            if(t < 3) {
                t ++;
                if(t == 3) {
                    frames += 1;
                    if (frames == 9) {
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
        Frieza.setPosition(160,105);

        rect = new Rectangle((int)Frieza.getX(), (int)Frieza.getY(), (int)Frieza.getWidth(), (int)Frieza.getHeight());

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        body = Main.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;

        shape.setAsBox(48* (float) Math.pow(Main.PPM, 2), 88* (float) Math.pow(Main.PPM, 2));

        body.createFixture(fdef).setUserData("Frieza");


        body.setTransform((float) rect.getX() + 3, (float) rect.getY() + 10, 0);


    }
}