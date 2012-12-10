/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import org.newdawn.slick.Graphics;
import starparty.component.Button;
import starparty.library.ShipInternals;
import starparty.library.Team;

/**
 *
 * @author Tyler
 */
public class TeamControls {
  int x;
  int y;
  int width;
  int height;
  
  Button move;
  Button move2;
  Button stop;
  
  TeamSelection team;
  NodeSelection node;
  
  ShipInternals shipInternals;
  
  public TeamControls() {
    move = new Button("Move") {
      @Override
      public void click() {
        boolean validRoom = node.get() != null;
        
        if (validRoom && team.get().location.id != node.get().id)
          team.get().move(shipInternals.getPath(team.get().location.id, node.get().id));
      }      
    };
    
    move2 = new Button("Move Imm.") {
      @Override
      public void click() {
      }      
    };
    
    stop = new Button("Stop") {
      @Override
      public void click() {
        team.get().stop();
      }      
    };
  }
  
  public void setShipInternals(ShipInternals shipInternals) {
    this.shipInternals = shipInternals;
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
    move.setLocation(x, y);
    move.setSize(200, 25);
    
    move2.setLocation(x, y + 40);
    move2.setSize(200, 25);
    
    stop.setLocation(x, y + 80);
    stop.setSize(200, 25);
  }
  
  public void draw(Graphics g) {
    if (team.get() != null) {
      g.setColor(Personnel.basicColor);
      move.draw(g);
      move2.draw(g);
      stop.draw(g);
    }
  }
  
  public void click(int x, int y) {
    move.click(x, y);
    move2.click(x, y);
    stop.click(x, y);
  }

  void setSelectedNode(NodeSelection node) {
    this.node = node;
  }

  void setSelectedTeam(TeamSelection team) {
    this.team = team;
  }
}
