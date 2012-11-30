/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.*;

/**
 *
 * @author Tyler
 */
public class ShipInternals {
  public Map<Integer, ShipNode> shipNodes;
  
  public List<ShipNode> getPath(int start, int end) {
    Set<ShipNode> visitedNodes = new HashSet<ShipNode>();
    Set<ShipNode> nodesToVisit = new HashSet<ShipNode>();
    
    List<ShipNode> path = new ArrayList<ShipNode>();
    
    
    
    
    return path;
  }
  
  private int getPath(ShipNode current, List<ShipNode> path, int cost, Set<ShipNode> visitedNodes) {
    visitedNodes.add(current);
    path.add(current);
    
    for (ShipPath testPath: current.paths) {
      ShipNode testNode = testPath.getEnd(current);
      
      if (!visitedNodes.contains(testNode)) {
        getPath(testNode, path, cost + testPath.cost, visitedNodes);
      }
    }
    
    return 0;
  }
}
