/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.List;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import starparty.utilities.ImageLoader;

/**
 *
 * @author Tyler
 */
public class Team {
  public String name;
  
  public ShipNode location;
  ShipNode destination;
  public Image icon;
  
  public static enum MoveStatus {STOPPED, STOPPING, MOVING};
  public MoveStatus moveStatus = MoveStatus.STOPPED;
  public int elapsed;
  List<ShipNode> path;
  
  public Team(String name, ShipNode location) {
    this.name = name;
    this.location = location;
    icon = ImageLoader.load("personnel/badge.png");
  }
  
  public void setLocation(ShipNode location) {
    this.location = location;
  }
  
  public void move(List<ShipNode> path) {
    if (moveStatus == MoveStatus.STOPPED) {
      this.path = path;
      moveStatus = MoveStatus.MOVING;
      
      ShipNode start = path.get(0);
      ShipNode end = path.get(1);
      currentSegment = new ShipPath(start, end, start.getCost(end));
      elapsed = 0;
      currentPathStart = 0;
    }
  }
  
  public ShipPath currentSegment;
  int currentPathStart;
  public void update(int delta) {
    switch (moveStatus) {
      case STOPPING:
      case MOVING:
        elapsed += delta;
        int costInMillis = currentSegment.cost * 1000;

        while (elapsed > costInMillis) {
          elapsed -= costInMillis;
          currentPathStart++;
          
          if (currentPathStart == path.size() - 1) {
            moveStatus = MoveStatus.STOPPED;
            location = path.get(currentPathStart);
            return;
          } else {
            currentSegment.start = currentSegment.end;
            currentSegment.end = path.get(currentPathStart + 1);
            currentSegment.cost = path.get(currentPathStart).getCost(currentSegment.end);
            costInMillis = currentSegment.cost * 1000;
          }
        }
        
        break;
    }
  }
}
