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
  public static InterstellarObject generatePlanet(String organization) {
    InterstellarObject o = new InterstellarObject(NameGenerator.generate("planet", "federation"), 0, 0, 0);
    o.type = "Planet";
    try {o.portrait = new Image("resources/interstellar_object/planet/earth.jpg");} catch (Exception e) {}
    try {o.icon = new Image("resources/interstellar_object/planet/earth_icon.png");} catch (Exception e) {}
    
    return o;
  }
  
  public static InterstellarObject generateShip(String organization) {
    Ship s = new Ship(NameGenerator.generate("ship", "federation"), 0, 0, 0);
    s.type = "Scout Ship";
    try {s.portrait = new Image("resources/interstellar_object/ship/scout.png");} catch (Exception e) {}
    try {s.icon = new Image("resources/interstellar_object/ship/scout_icon.png");} catch (Exception e) {}
    
    s.maxHullStrength = 500;
    s.maxShieldStrength = 200;
    
    s.hullStrength = s.maxHullStrength;
    s.shieldStrength = s.maxShieldStrength;
    
    return s;
  }
}
