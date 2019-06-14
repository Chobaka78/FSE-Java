package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Frieza" && Main.mode != "gameover" && Goku.fstat[0] >0 && Main.EnemyType ==2){
            Battle.enemy = 3;
            Main.mode = "battle";
        }

        if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Nappa" && Main.mode != "gameover" && Goku.fstat[0] >0 && Main.EnemyType ==0){
            Battle.enemy = 3;
            Main.mode = "battle";
        }

        if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Raditz" && Main.mode != "gameover" && Goku.fstat[0] >0 && Main.EnemyType == 1){
            Battle.enemy = 3;
            Main.mode = "battle";
        }

        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Interaction"){
            Main.type = "Shop";
            Main.moveBody = true;

        }

        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Building-Intraction"){
            Main.type = "House";
            Main.moveBody = true;

        }

        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Mapchange"){
            Main.type = "WeirdPlace";
            Main.moveBody = true;

        }

        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Map2"){
            Main.type = "Map2";
            Main.moveBody = true;

        }

        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Un-Interact"){
            Main.type = "open";
            Main.moveBody = true;

        }
        else if(contact.getFixtureA().getUserData() == "Player" && contact.getFixtureB().getUserData() == "Trunks"){
            System.out.println("WOW I HIT TRUNKS");
            Items.HitTrunks = true;
        }

        Gdx.app.log("Begin Contact", "");
    }

    @Override
    public void endContact(Contact contact) {

        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
