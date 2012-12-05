/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;
import starparty.component.Toggle;
import starparty.library.ShipRoom;

/**
 *
 * @author Tyler
 */
public class RoomControls {
  TeamControls teamControls;
  private List<Toggle> buttons;
  private List<ShipRoom> rooms;
  int x;
  int y;
  int columnWidth;
  int columns;
  
  private static int PADDING = 8;
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setTeamControls(TeamControls teamControls) {
    this.teamControls = teamControls;
  }
  
  public void setSize(int columnWidth, int columns) {
    this.columnWidth = columnWidth;
    this.columns = columns;
  }
  
  public void setRooms(List<ShipRoom> rooms) {
    this.rooms = rooms;
    buttons = new ArrayList<Toggle>();
    
    int count = 0;
    Toggle t;
    for (final ShipRoom room: rooms) {
      int column = count % columns;
      int row = count / columns;
      
      buttons.add(t = new Toggle(room.name) {
        @Override
        public void click() {
          for (Toggle button: buttons) {
            if  (button != this)
              button.selected = false;
          }
          
          teamControls.setSelectedNode(room.node);
          System.out.println(room.name);
        }
      });
      
      t.setLocation(x + column * columnWidth, y + row * 32);
      t.setSize(columnWidth - PADDING, 32 - PADDING);
      count++;
    }
  }
  
  public void draw(Graphics g) {
    for (Toggle button: buttons) {
      button.draw(g);
    }
  }
  
  public boolean click(int x, int y) {
    for (Toggle button: buttons)
      if (button.click(x, y))
        return true;
    
    return false;
  }
}
