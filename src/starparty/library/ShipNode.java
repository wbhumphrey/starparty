/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class ShipNode {
  public int id;
  public int x;
  public int y;
  public List<ShipPath> paths = new ArrayList<ShipPath>();
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public String toString() {
    return id + "";
  }
}
