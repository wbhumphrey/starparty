/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;
import starparty.component.Button;
import starparty.component.ButtonGroup;
import starparty.library.ShipInternals;

/**
 *
 * @author Tyler
 */
public class TeamControls {
  int x;
  int y;
  int width;
  int height;
  
  ButtonGroup controls;
  
  TeamSelection team;
  NodeSelection node;
  
  ShipInternals shipInternals;
  
  public TeamControls() {
    List<Button> buttons = new ArrayList<Button>();
    
    buttons.add(new Button("Move", Button.NORMAL) {
      @Override
      public void click() {
        boolean validRoom = node.get() != null;
        
        if (validRoom && team.get().location.id != node.get().id)
          team.get().move(shipInternals.getPath(team.get().location.id, node.get().id));
      }      
    });
    
    buttons.add(new Button("Stop", Button.NORMAL) {
      @Override
      public void click() {
        team.get().stop();
      }      
    });
    
    controls = new ButtonGroup(buttons);
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
    controls.setLocation(x, y);
  }
  
  public void draw(Graphics g) {
    if (team.get() != null) {
      controls.draw(g);
    }
  }
  
  public void click(int x, int y) {
    controls.click(x, y);
  }

  void setSelectedNode(NodeSelection node) {
    this.node = node;
  }

  void setSelectedTeam(TeamSelection team) {
    this.team = team;
  }
}
