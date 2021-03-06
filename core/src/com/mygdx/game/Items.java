package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.awt.*;
import java.util.Set;

public class Items {
    private Sprite Trunks;

    public static Texture fontback, Inventory, oneStar, TwoStar,ThreeStar, FourStar, FiveStar, SixStar, SevenStar; // these are the dragon ball textures

    Body body; // body for trunks

    Rectangle rect;

    private Player player;


    public static boolean HitTrunks = false, Cancel = false, purchase = false;

    public Items(){
        player = new Player();
        Trunks = new Sprite(new Texture("Assets/Sprites/Trunks_Open/trunks.png"));
        Inventory =new Texture("Assets/Inventory.png");
        fontback = new Texture("Assets/Fonts/ShopFontback.png");

        oneStar = new Texture("Assets/DragonBalls/1Star.png");
        TwoStar = new Texture("Assets/DragonBalls/2Star.png");
        ThreeStar = new Texture("Assets/DragonBalls/3Star.png");
        FourStar = new Texture("Assets/DragonBalls/4Star.png");
        FiveStar = new Texture("Assets/DragonBalls/5Star.png");
        SixStar = new Texture("Assets/DragonBalls/6Star.png");
        SevenStar = new Texture("Assets/DragonBalls/7Star.png");
        createBody();
    }

    public void update(SpriteBatch batch){
        purchase = false;
        Trunks.setPosition(body.getPosition().x,body.getPosition().y);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){ // shop can be exited
            Cancel = true;
            HitTrunks = false;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.H) && !purchase){
            Battle.inventory[0] += 1;
            purchase = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && !purchase){
            Battle.inventory[1] += 1;
            purchase = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && !purchase){
            purchase = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            Battle.sevenstar = true;
            purchase = true;
        }


        render(batch);
    }

    public void createBody(){ // creating trunks body
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

    public void render(SpriteBatch batch){ // render method will render if inside the shop and will also render the dragon balls
        batch.draw(Trunks,body.getPosition().x - Trunks.getWidth() * (float) Math.pow(Main.PPM,2),body.getPosition().y - Trunks.getHeight() * (float) Math.pow(Main.PPM, 2), Trunks.getWidth() * (float) Math.pow(Main.PPM, 2) * 3, Trunks.getHeight() * (float) Math.pow(Main.PPM, 2) * 3);
        if(HitTrunks && !Cancel){
            batch.draw(Items.fontback,Main.camera.position.x - 54,Main.camera.position.y - 33,fontback.getWidth()* (float) Math.pow(Main.PPM,2) + 89,fontback.getHeight() *(float) Math.pow(Main.PPM,2) + 5);
            batch.draw(Inventory,Main.camera.position.x - 30,Main.camera.position.y - 20,Inventory.getWidth() * (float)Math.pow(Main.PPM,2), Inventory.getHeight() * (float) Math.pow(Main.PPM,2));
        }
    }
}
