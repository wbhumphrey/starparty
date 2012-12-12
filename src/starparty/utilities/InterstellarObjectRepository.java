/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import starparty.library.InterstellarObject;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class InterstellarObjectRepository {
  /* Class attributes and methods*/

  private static InterstellarObjectRepository repository;

  public static InterstellarObjectRepository initialize(String filename) {
    try {
      repository = new Gson().fromJson(new FileReader(filename), InterstellarObjectRepository.class);
      repository.initialize();
      return repository;
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }

  public static InterstellarObjectRepository getFactory() {
    return repository;
  }

  public static InterstellarObject generatePlanet(String organization) {
    InterstellarObject o = new InterstellarObject(NameGenerator.generate("planet", "federation"), 0, 0);
    o.type = "Planet";
    o.portrait = ImageLoader.load("interstellar_object/planet/earth.jpg");
    o.icon = ImageLoader.load("interstellar_object/planet/earth_icon.png");

    return o;
  }

  public static Ship generateShip(String organization) {
    Ship s = new Ship(NameGenerator.generate("ship", "federation"), 0, 0);
    s.type = "Scout Ship";
    s.portrait = ImageLoader.load("interstellar_object/ship/scout.png");
    s.icon = ImageLoader.load("interstellar_object/ship/scout_icon.png");

    s.setMaxHullStrength(500);
    s.setMaxShieldStrength(200);

    s.hullStrength = s.getMaxHullStrength();
    s.shieldStrength = s.getMaxShieldStrength();

    return s;
  }
  /**
   * Instance attributes and methods
   */
  private Map<String, Ship> ships = new LinkedHashMap<String, Ship>();

  public Ship getShip(String name) {
    return ships.get(name);
  }

  public Collection<Ship> getShips() {
    return ships.values();
  }

  public List<InterstellarObject> values() {
    List<InterstellarObject> list = new ArrayList<InterstellarObject>();
    list.addAll(ships.values());
    
    return list;
  }

  public void initialize() {
    Random r = new Random();
    for (String name : ships.keySet()) {
      Ship ship = ships.get(name);
      ship.initialize();
      ship.name = name;
      ship.portrait = ImageLoader.load("interstellar_object/ship/scout.png");
      ship.icon = ImageLoader.load("interstellar_object/ship/scout_icon.png");
      ship.setLocation(r.nextFloat() * 500 - 250, r.nextFloat() * 500 - 250);
    }
  }
}
