/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import org.newdawn.slick.Image;
import starparty.library.InterstellarObject;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class InterstellarObjectFactory {
  
//  public static InterstellarObject generate(String name){
//    
//  }
  
  public static InterstellarObject generatePlanet(String organization) {
    InterstellarObject o = new InterstellarObject(NameGenerator.generate("planet", "federation"), 0, 0, 0);
    o.type = "Planet";
    o.portrait = ImageLoader.load("resources/interstellar_object/planet/earth.jpg");
    o.icon = ImageLoader.load("resources/interstellar_object/planet/earth_icon.png");

    return o;
  }

  public static InterstellarObject generateShip(String organization) {
    Ship s = new Ship(NameGenerator.generate("ship", "federation"), 0, 0, 0);
    s.type = "Scout Ship";
    s.portrait =  ImageLoader.load("resources/interstellar_object/ship/scout.png");
    s.icon = ImageLoader.load("resources/interstellar_object/ship/scout_icon.png");
    
    s.setMaxHullStrength(500);
    s.setMaxShieldStrength(200);

    s.hullStrength = s.getMaxHullStrength();
    s.shieldStrength = s.getMaxShieldStrength();

    return s;
  }
}
