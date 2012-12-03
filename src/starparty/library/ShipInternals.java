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
    PriorityQueue<PartialPath> unmarkedPaths = new PriorityQueue<PartialPath>();
    Set<ShipNode> unmarkedNodes = new HashSet<ShipNode>();
    Map<ShipNode, PartialPath> nodePathLink = new HashMap<ShipNode, PartialPath>();

    ShipNode startNode = shipNodes.get(start);
    ShipNode endNode = shipNodes.get(end);

    for (ShipNode node : shipNodes.values()) {
      PartialPath unmarkedPath = new PartialPath(node);

      if (node == startNode) {
        unmarkedPath.cost = 0;
        unmarkedPath.nodes.add(startNode);
      }

      unmarkedPaths.add(unmarkedPath);
      unmarkedNodes.add(node);
      nodePathLink.put(node, unmarkedPath);
    }
    
    // System.out.println(shipNodes.values());
    // System.out.println(unmarkedPaths);

    PartialPath currentPath;
    ShipNode currentNode;
    while ((currentPath = unmarkedPaths.poll()) != null) {
      currentNode = currentPath.node;
      unmarkedNodes.remove(currentNode);

      for (ShipPath path : currentNode.paths) {
        ShipNode destination = path.getDestination(currentNode);

        if (unmarkedNodes.contains(destination)) {
          // System.out.println("Checking " + currentNode.id + " -> " + destination.id);
          PartialPath cachedPath = nodePathLink.get(destination);
          int currentCost = cachedPath.cost;
          int newCost = currentPath.cost + path.cost;

          if (newCost < currentCost) {
            unmarkedPaths.remove(cachedPath);
            
            cachedPath.cost = newCost;
            cachedPath.nodes = new ArrayList<ShipNode>(currentPath.nodes);
            cachedPath.nodes.add(destination);
            // System.out.println(cachedPath.nodes);
            unmarkedPaths.add(cachedPath);
          }
        }
      }
    }

    return nodePathLink.get(endNode).nodes;
  }
}

class PartialPath implements Comparable<PartialPath> {

  List<ShipNode> nodes = new ArrayList<ShipNode>();
  int cost = Integer.MAX_VALUE;
  ShipNode node;

  public PartialPath(ShipNode destination) {
    this.node = destination;
  }

  @Override
  public int compareTo(PartialPath o) {
    return cost - o.cost;
  }
}