package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class battle {
    public battle(){
        if(Main.Game == "Level1" && Main.Mode == "Battle"){
            if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                Player.fstat[0] = Player.fstat[0] - Player.gstat[2];
                System.out.println(Player.fstat[0]);

            }

        }
    }
}
