package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud {
    public Sprite box = new Sprite(new Texture("Assets/Fonts/box.png"));

    public Hud(){

    }

    public void render (SpriteBatch batch){
        batch.draw(box,300,200);

    }

    public void update (SpriteBatch batch){
        render(batch);
    }
}
