/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty;

import java.util.List;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class Sector {
  public Player player;
  public Ship ship;
  public List<InterstellarObject> interstellarObjects;
  
  public void update(int delta) {
      ship.update(delta);
  }
}
