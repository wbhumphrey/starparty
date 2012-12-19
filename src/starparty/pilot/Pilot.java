 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.pilot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import starparty.Sector;
import starparty.Station;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.library.Ship;
import starparty.utilities.Distance;
import starparty.utilities.FontLoader;
import starparty.utilities.InterstellarObjectRepository;

/**
 *
 * @author Tyler
 */
public class Pilot extends Station {
  public static Sector sector;
  private Ship ship;
  private Player player;
  public static UnicodeFont smallFont;
  
  Point center;
  List<InterstellarObject> objects;
  Starfield starfield;

  public Pilot(Sector sector) {
    this.sector = sector;
  }

  Shape shape = new Polygon(new float[]{0, 4, -8, 8, 0, -10, 8, 8});
  @Override
  public void draw(Graphics g) {
    starfield.draw(g);
    
    g.setColor(Color.red);
    g.drawString("theta: " + Math.round(ship.direction.getDegrees()), 10, 25);
    g.drawString("speed: " + Distance.magnitude(ship.getVelocity().x, ship.getVelocity().y), 10, 40);
    g.drawString("x: " + Math.round(ship.getLocation().x), 10, 55);
    g.drawString("y: " + Math.round(ship.getLocation().y), 10, 70);
    
    for (InterstellarObject o: objects) {
      int drawX = (int) (center.getX() + (o.getLocation().x - ship.getLocation().x));
      int drawY = (int) (center.getY() - (o.getLocation().y - ship.getLocation().y));
      g.drawImage(o.icon, drawX, drawY);
    }
    
    Transform t = Transform.createTranslateTransform(center.getX(), center.getY());
    Transform a = Transform.createRotateTransform((float) -(ship.direction.getRadians() - (Math.PI / 2)), center.getX(), center.getY());
    g.setColor(Color.cyan);
    g.fill(shape.transform(t).transform(a));
  }

  @Override
  public void init(GameContainer arg0) throws SlickException {
    smallFont = FontLoader.load("TCM_____.TTF", 15);
    
    center = new Point(1024 / 2, 700 / 2);
    player = sector.player;
    ship = player.ship;
    ship.direction.setRadians(Math.PI / 2);
    
    InterstellarObject o;
    objects = sector.interstellarObjects;
    
    starfield = new Starfield(ship);
    starfield.setLocation(0,0);
    starfield.setSize(1024, 700);
    starfield.init();
  }

  @Override
  public void update(int delta) {
    if (keyStates.contains(Input.KEY_LEFT)) {
      ship.rotateCW(delta);
    } else if (keyStates.contains(Input.KEY_RIGHT)) {
      ship.rotateCCW(delta);
    }
    
    if (keyStates.contains(Input.KEY_SPACE)) {
      ship.brake(delta);
    }
    
    if (keyStates.contains(Input.KEY_UP)) {
      ship.accelerate(delta);
    }
  }
  Set<Integer> keyStates = new HashSet<Integer>();

  @Override
  public void keyPressed(int key, char c) {
    keyStates.add(key);
  }

  @Override
  public void keyReleased(int key, char c) {
    keyStates.remove(key);
  }
}
