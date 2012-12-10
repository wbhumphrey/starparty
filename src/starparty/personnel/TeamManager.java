/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import starparty.library.Team;

/**
 *
 * @author Tyler
 */
public class TeamManager {
  List<Team> teams;
  TeamSelection selectedTeam;
  
  int x;
  int y;
  int width;
  int height;
  
  public TeamManager(List<Team> teams) {
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
    int count = 0;

    for (Team team: teams) {
      int row = count % 4;
      int column = count / 4;
      
      if (team == selectedTeam.get()) {
        g.setColor(Color.darkGray);
        g.fillRect(x + 220 * column, y + 38 * row, 220, 38);
        
      }
      
      g.setFont(Personnel.basicFont);
      g.setColor(Personnel.basicColor);
      g.drawString(team.name, x + 220 * column, y + 38 * row);
      
      count++;
    }
  }
  
  Rectangle r = new Rectangle(0, 0, 0, 0);
  public boolean click(int x, int y) {
    int count = 0;

    for (Team team: teams) {
      int row = count % 4;
      int column = count / 4;
      
      r.setBounds(this.x + 220 * column, this.y + 38 * row, 200, 38);
      if (r.contains(x, y)) {
        selectedTeam.set(team);
        return true;
      }
      
      count++;
    }
    
    return false;
  }

  void setSelectedTeam(TeamSelection selectedTeam) {
    this.selectedTeam = selectedTeam;
  }
}
