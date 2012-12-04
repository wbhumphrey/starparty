/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

/**
 *
 * @author Tyler
 */
public class ShipRoom {
  public String name;
  public String id;
  public ShipNode node;
  public int x;
  public int y;
  
  public ShipRoom(String id, String name) {
    this.id = id;
    this.name = name;
  }

  void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
