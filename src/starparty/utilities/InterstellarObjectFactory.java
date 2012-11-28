/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import org.newdawn.slick.Image;
import starparty.library.InterstellarObject;

/**
 *
 * @author Tyler
 */
public class InterstellarObjectFactory {
  public static InterstellarObject geteratePlanet(String organization) {
    InterstellarObject o = new InterstellarObject(NameGenerator.generate("planet", "federation"), 0, 0, 0);
    o.type = "Planet";
    try {o.portrait = new Image("resources/interstellar_object/planet/earth.jpg");} catch (Exception e) {}
    try {o.icon = new Image("resources/interstellar_object/planet/earth_icon.png");} catch (Exception e) {}
    
    return o;
  }
  
  public static InterstellarObject geterateShip(String organization) {
    InterstellarObject o = new InterstellarObject(NameGenerator.generate("ship", "federation"), 0, 0, 0);
    o.type = "Scout Ship";
    try {o.portrait = new Image("resources/interstellar_object/ship/scout.png");} catch (Exception e) {}
    try {o.icon = new Image("resources/interstellar_object/ship/scout_icon.png");} catch (Exception e) {}
    
    return o;
  }
}
