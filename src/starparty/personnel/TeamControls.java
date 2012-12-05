/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import org.newdawn.slick.Graphics;
import starparty.component.Button;
import starparty.library.ShipInternals;
import starparty.library.ShipNode;
import starparty.library.ShipRoom;
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
  
  Team selectedTeam;
  ShipNode selectedNode;
  Button move;
  Button move2;
  Button stop;
  
  ShipInternals shipInternals;
  
  public TeamControls() {
    move = new Button("Move") {
      @Override
      public void click() {
        boolean validRoom = selectedNode != null;
        
        if (validRoom && selectedTeam.location.id != selectedNode.id)
          selectedTeam.move(shipInternals.getPath(selectedTeam.location.id, selectedNode.id));
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
      }      
    };
  }
  
  public void setSelectedTeam(Team team) {
    selectedTeam = team;
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
    move.setSize(200, 45);
    
    move2.setLocation(x, y + 50);
    move2.setSize(200, 45);
    
    stop.setLocation(x, y + 100);
    stop.setSize(200, 45);
  }
  
  public void draw(Graphics g) {
    if (selectedTeam != null) {
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

  void setSelectedNode(ShipNode node) {
    this.selectedNode = node;
  }
}
