package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Map {

    int width  = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

    private TmxMapLoader mapLoader;
    private com.badlogic.gdx.maps.tiled.TiledMap map;
    static OrthogonalTiledMapRenderer renderer;
    public static OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer b2dr, b2dr2;
    public Body body1;

    public Map(){
        camera = new OrthographicCamera();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();
        b2dr2 = new Box2DDebugRenderer();
        // these are the bodies for the objects

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //this is a rect body that will be used for player
        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(10,20);
        FixtureDef fdef1 = new FixtureDef();
        fdef1.shape = shape1;
        body1 = world.createBody(bdef2);
        body1.setTransform(192,175,0);
        body1.createFixture(fdef1).setUserData("Box");

        // for buildings
        for(MapObject obj : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Building");

        }

        //for trees
        for(MapObject obj : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        // for extras
        for(MapObject obj : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) obj).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        world.setContactListener(new WorldContactListener());
    }
    public void render(OrthographicCamera camera){
        renderer.setView(camera);
        renderer.render();
        b2dr.render(world,camera.combined);
        b2dr2.render(world,camera.combined);
    }

    public void update(OrthographicCamera camera){
        body1.setTransform(Main.vx,Main.vy,0);
        world.step(1.60f,6,2);
        camera.setToOrtho(false,width,height);
        camera.update();
        render(camera);
    }

}
