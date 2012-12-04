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
  public ShipNode start;
  public ShipNode end;
  public int cost;
  
  public ShipPath(ShipNode start, ShipNode end, int cost) {
    this.start = start;
    this.end = end;
    this.cost = cost;
  }

  ShipPath() {
  }

  ShipNode getEnd(ShipNode current) {
    return (current == start ? end : start);
  }

  ShipNode getDestination(ShipNode currentNode) {
    return currentNode == start ? end : start;
  }
  
  public boolean has(ShipNode node) {
    return start == node || end == node;
  }
}
