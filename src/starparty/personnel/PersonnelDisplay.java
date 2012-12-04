/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.library.*;

/**
 *
 * @author Tyler
 */
public class PersonnelDisplay {
  int x;
  int y;
  int width;
  int height;
  List<Team> teams;
  ShipInternals shipInternals;
  
  public PersonnelDisplay(ShipInternals shipInternals, List<Team> teams) {
    this.shipInternals = shipInternals;
    this.teams = teams;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.red);
    g.setFont(Personnel.smallFont);
    for (ShipNode node: shipInternals.nodes.values()) {
      g.fillOval(x + node.x - 1, y + node.y - 2, 4, 4);
      g.drawString("" + node.id, x + node.x, y + node.y);
    }
    
    for (ShipRoom room: shipInternals.rooms) {
      g.drawString(room.name, x + room.x, y + room.y);
    }
    
    for (Team team: teams) {
      switch (team.moveStatus) {
        case STOPPED:
          g.drawImage(team.icon, team.location.x + x - 16, team.location.y + y - 16);
          break;

        case MOVING:
        case STOPPING:
          ShipPath path = team.currentSegment;
          double percentComplete = (0.0 + team.elapsed) / (1000 * path.cost);
          
          int drawX = (int) ((percentComplete * (path.end.x - path.start.x)) + path.start.x);
          int drawY = (int) ((percentComplete * (path.end.y - path.start.y)) + path.start.y);
          g.drawImage(team.icon, x + drawX - 16, y + drawY - 16);
          break;
      }
    }
  }
}
