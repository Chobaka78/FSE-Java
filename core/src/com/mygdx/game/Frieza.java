package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.util.ArrayList;

public class Frieza {
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

    public Frieza(){
        Frieza = new Sprite();
        createBody();
        Load();
    }

    public void Load(){
        for(int k = 0; k < 1; k ++) {
            tmp = new ArrayList<Texture>();
            for (int j = 0; j < list[k]; j++) {
                tmp.add(new Texture("Assets/Sprites/Frieza/Frieza" + j + ".png"));
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
        batch.draw(Frieza,body.getPosition().x - Frieza.getWidth() * (float) Math.pow(Main.PPM,2),body.getPosition().y - Frieza.getHeight() * (float) Math.pow(Main.PPM, 2), Frieza.getWidth() * (float) Math.pow(Main.PPM, 2) * 3/2, Frieza.getHeight() * (float) Math.pow(Main.PPM, 2) * 3/2);

    }

    public int moveFrames(){ // this is the animation for the movement frames the character
        if(frames < 9){
            if(t < 2) {
                t ++;
                if(t == 2) {
                    frames += 1;
                    if (frames == 9) {
                        frames = 0;
                        Main.animation1 = false;
                    }
                    t = 0;
                }
            }
        }
        return frames;
    }

    public void createBody(){
        Frieza.setPosition(554,367);

        rect = new Rectangle((int) Frieza.getX(), (int) Frieza.getY(), (int) Frieza.getWidth(), (int) Frieza.getHeight());

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = Main.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.shape = shape;

        shape.setAsBox(5 * (float) Math.pow(Main.PPM, 2), 10 * (float) Math.pow(Main.PPM, 2));

        body.createFixture(fdef).setUserData("Frieza");


        body.setTransform((float) rect.getX() * Main.PPM, (float) rect.getY() * Main.PPM, 0);

        MassData thiccc = new MassData();
        thiccc.mass = 90f;//in kg
        body.setMassData(thiccc);


    }
}
