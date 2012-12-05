/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import starparty.component.FixedCircle;
import starparty.component.Tooltip;
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
  List<Tooltip> tooltips;
  ShipNode selectedNode;
  TeamControls teamControls;
  
  public PersonnelDisplay(ShipInternals shipInternals, List<Team> teams) {
    this.shipInternals = shipInternals;
    this.teams = teams;
  }
  
  public void setTeamControls(TeamControls teamControls) {
    this.teamControls = teamControls;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
    
    init();
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
    
    init();
  }
  
  public void init() {
    tooltips = new ArrayList<Tooltip>();
    
    for (ShipRoom room: shipInternals.rooms) {
      Tooltip t;
      tooltips.add(t = new Tooltip(room.name));
      t.setTriggerLocation(new FixedCircle(x + room.node.x, y + room.node.y, 10));
      t.setLocation(x + room.node.x, y + room.node.y - t.height - 8);
    }
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.red);
    g.setFont(Personnel.smallFont);
    for (ShipNode node: shipInternals.nodes.values()) {
      g.fillOval(x + node.x - 2, y + node.y - 3, 6, 6);
    }
    
    for (ShipRoom room: shipInternals.rooms) {
    }
    
    for (Tooltip t: tooltips) {
      t.draw(g);
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

  Circle roomCircle = new FixedCircle(0, 0, 0);
  public boolean click(int x, int y) {
    for (ShipNode node: shipInternals.nodes.values()) {
      roomCircle.setLocation(this.x + node.x - 10, this.y + node.y - 10);
      roomCircle.setRadius(10);
      
      if (roomCircle.contains(x, y)) {
        teamControls.setSelectedNode(node);
        return true;
      }
    }
    
    return false;
  }
}
