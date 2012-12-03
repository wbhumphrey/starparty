/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler
 */
public class Schematic {
  public List<Node> nodes;
  public List<Path> paths;
  public List<Room> rooms;
  
  public ShipInternals getShipInternals() {
    Map<Integer, ShipNode> shipNodes = new HashMap<Integer, ShipNode>();
    ShipNode shipNode;
    
    for (Node node: nodes) {
      shipNodes.put(node.id, shipNode = new ShipNode());
      shipNode.id = node.id;
      shipNode.setLocation(node.x, node.y);
    }
    
    for (Path path: paths) {
      ShipNode start = shipNodes.get(path.start);
      ShipNode end = shipNodes.get(path.end);
      
      ShipPath shipPath = new ShipPath(start, end, path.cost);
      start.paths.add(shipPath);
      end.paths.add(shipPath);
    }
    
    ShipInternals internals = new ShipInternals();
    internals.shipNodes = shipNodes;
    return internals;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public List<Path> getPaths() {
    return paths;
  }

  public void setPaths(List<Path> paths) {
    this.paths = paths;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }
  
  public static class Node {
    int id;
    int x;
    int y;
    List<String> rooms;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public List<String> getRooms() {
      return rooms;
    }

    public void setRooms(List<String> rooms) {
      this.rooms = rooms;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }
  }
  
  public static class Path {
    int start;
    int end;
    int cost;

    public int getCost() {
      return cost;
    }

    public void setCost(int cost) {
      this.cost = cost;
    }

    public int getEnd() {
      return end;
    }

    public void setEnd(int end) {
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public void setStart(int start) {
      this.start = start;
    }
  }
  
  public static class Room {
    String id;
    int x;
    int y;
    String name;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }
  }
}
