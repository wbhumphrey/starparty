/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;
import starparty.library.InterstellarObject;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class InterstellarObjectFactory {
  /* Class attributes and methods*/

  private static InterstellarObjectFactory factory;

  public static void generateFactory(String filename) {
    filename = "resources/interstellar_object/interstellar_object_schematic.json";
    try {
      GsonBuilder gson = new GsonBuilder();
      gson.registerTypeAdapter(HashMap.class, new JsonDeserializer <HashMap>() {
        @Override
        public HashMap deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
          throw new UnsupportedOperationException("Not supported yet.");
        }
      });
      
      factory = new Gson().fromJson(new FileReader(filename), InterstellarObjectFactory.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static InterstellarObjectFactory getFactory() {
    return factory;
  }

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
    s.portrait = ImageLoader.load("resources/interstellar_object/ship/scout.png");
    s.icon = ImageLoader.load("resources/interstellar_object/ship/scout_icon.png");

    s.setMaxHullStrength(500);
    s.setMaxShieldStrength(200);

    s.hullStrength = s.getMaxHullStrength();
    s.shieldStrength = s.getMaxShieldStrength();

    return s;
  }
  
  /**
   * Instance attributes and methods
   */
  private HashMap<String, Ship> ships = new HashMap<String, Ship>();
  
  public Ship getShip(String name) {
    return ships.get(name);
  }

  public Collection<Ship> getShips() {
    return ships.values();
  }

  public void setShips(HashMap<String,Ship> ships) {
    //ships.put(ship.name, ship);
  }
}
