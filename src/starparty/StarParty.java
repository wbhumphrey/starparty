/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty;

import java.util.HashMap;
import org.newdawn.slick.*;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.personnel.Personnel;
import starparty.pilot.Pilot;
import starparty.tactical.Tactical;
import starparty.utilities.InterstellarObjectRepository;

/**
 *
 * @author Tyler
 */
public class StarParty extends BasicGame {
  Sector sector;
  Station station;
  Personnel personnel;
  HashMap<String, Station> stations = new HashMap<String, Station>();
  
  public static void main(String... args) {
    try {
      AppGameContainer app = new AppGameContainer(new StarParty());
      app.setDisplayMode(1024, 700, false);
      app.setSmoothDeltas(true);
      app.setTargetFrameRate(60);
      app.setShowFPS(true);
      app.start();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public StarParty() {
    super("Star Party");
  }
  
  @Override
  public void init(GameContainer gc) throws SlickException {
    sector = new Sector();
    Player player = new Player(0, 0);
    sector.player = player;
    InterstellarObjectRepository repo = InterstellarObjectRepository.initialize(
            "resources/interstellar_object/interstellar_object_schematic.json");
    player.ship = repo.getShip("Domenica");
    player.ship.setLocation(0, 0);
    sector.ship = player.ship;
    sector.interstellarObjects = repo.values();
    InterstellarObject o;
    sector.interstellarObjects.add(o = InterstellarObjectRepository.generatePlanet("federation"));
    o.setLocation(0, 250);
    sector.interstellarObjects.remove(player.ship);
    
    personnel = new Personnel();
    personnel.init(gc);
    
    Station s;
    stations.put("Tactical", s = new Tactical(sector));
    s.init(gc);
    
    stations.put("Pilot", s = new Pilot(sector));
    s.init(gc);
  }

  @Override
  public void update(GameContainer gc, int delta) throws SlickException {
    sector.update(delta);
    
    if (station != null) {
      station.update(delta);
    } else {
      personnel.update(delta);
    }
  }

  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
    if (station != null) {
      station.draw(g);
    } else {
      personnel.draw(g);
    }
  }
  
  @Override
  public void keyPressed(int key, char c) {
    if (station != null) {
      if (key == Input.KEY_ESCAPE) {
        station = null;
        return;
      }
      
      station.keyPressed(key, c);
    } else {
      switch(key) {
        case Input.KEY_T:
          station = stations.get("Tactical");
          break;
          
        case Input.KEY_P:
          station = stations.get("Pilot");
          break;
      }
      
      if (station != null)
        return;
      
      personnel.keyPressed(key, c);
    }
  }
  
  @Override
  public void keyReleased(int key, char c) {
    if (station != null) {
      station.keyReleased(key, c);
    } else {
      personnel.keyReleased(key, c);
    }
  }
  
  @Override
  public void mouseClicked(int button, int x, int y, int clickCount) {
    if (station != null) {
      station.mouseClicked(button, x, y, clickCount);
    } else {
      personnel.mouseClicked(button, x, y, clickCount);
    }
  }
  
  @Override
  public void mousePressed(int button, int x, int y) {
    if (station != null) {
      station.mousePressed(button, x, y);
    } else {
      personnel.mousePressed(button, x, y);
    }
  }
  
  @Override
  public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    if (station != null) {
      station.mouseMoved(oldx, oldy, newx, newy);
    } else {
      personnel.mouseMoved(oldx, oldy, newx, newy);
    }
  }
}
