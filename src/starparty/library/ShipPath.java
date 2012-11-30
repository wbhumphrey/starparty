/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

/**
 *
 * @author Tyler
 */
public class ShipPath {
  ShipNode start;
  ShipNode end;
  int cost;
  
  public ShipPath(ShipNode start, ShipNode end, int cost) {
    this.start = start;
    this.end = end;
    this.cost = cost;
  }

  ShipNode getEnd(ShipNode current) {
    return (current == start ? end : start);
  }
}
