package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;


import java.awt.*;

public class Items {
    private Sprite Trunks;

    public static Sprite fontback;

    public int [] inventory = new int [] {5,5,3};

    Body body;

    Rectangle rect;

    String text;

    public  BitmapFont font;

    private Player player;

    public static boolean HitTrunks = false;

    public Items(){
        player = new Player();

        //font = new BitmapFont(Gdx.files.internal("Assets/Fonts/n.fnt"));
        //font.setColor(Color.BLACK);

        Trunks = new Sprite(new Texture("Assets/Sprites/Trunks_Open/trunks.png"));
        fontback = new Sprite(new Texture("Assets/Fonts/box.png"));
        text = "hi goku welcome to my store";
        createBody();
    }

    public void update(SpriteBatch batch){
        Trunks.setPosition(body.getPosition().x,body.getPosition().y);

        render(batch);
    }

    public void createBody(){
        Trunks.setPosition(747,147);

        rect = new Rectangle((int)Trunks.getX(), (int)Trunks.getY(), (int)Trunks.getWidth(), (int)Trunks.getHeight());

        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.StaticBody;
        body = Main.world.createBody(bdef2);

        FixtureDef fdef1 = new FixtureDef();
        PolygonShape shape1 = new PolygonShape();

        fdef1.shape = shape1;

        shape1.setAsBox(16* (float) Math.pow(Main.PPM, 2), 28* (float) Math.pow(Main.PPM, 2));

        body.createFixture(fdef1).setUserData("Trunks");

        body.setTransform((float) rect.getX(), (float) rect.getY(), 0);

    }

    public void render(SpriteBatch batch){
        batch.draw(Trunks,body.getPosition().x - Trunks.getWidth() * (float) Math.pow(Main.PPM,2),body.getPosition().y - Trunks.getHeight() * (float) Math.pow(Main.PPM, 2), Trunks.getWidth() * (float) Math.pow(Main.PPM, 2) * 3, Trunks.getHeight() * (float) Math.pow(Main.PPM, 2) * 3);
        if(Items.HitTrunks){
           // batch.draw(fontback,400,300);
            batch.draw(Items.fontback,player.getX() - 58,player.getY() - 28,fontback.getWidth()* (float) Math.pow(Main.PPM,2),fontback.getHeight() *(float) Math.pow(Main.PPM,2));
            //font.draw(batch,text,player.getX() - 58,player.getY() - 28);


        }
    }


}
