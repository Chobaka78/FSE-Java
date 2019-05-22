package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class WorldCreator {
    Body body;
    public WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        // for buildings
        for(MapObject obj : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);

            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Building");

        }

        //for trees
        for(MapObject obj : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Trees");

        }
        // for extras
        for(MapObject obj : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Extra");

        }
        // for walls
        for(MapObject obj : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set(rect.getX() * Main.PPM + rect.getWidth() / 2 * Main.PPM, rect.getY() * Main.PPM + rect.getHeight() / 2 * Main.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 * Main.PPM, rect.getHeight() / 2 * Main.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Wall");

        }

        // for Interactions
//        for(MapObject obj : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/2);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
//            fdef.shape = shape;
//            body.createFixture(fdef).setUserData("Interact");
//
//        }

    }
}
