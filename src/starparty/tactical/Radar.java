/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.utilities.Distance;

/**
 *
 * @author Tyler
 */
public class Radar {
  int x;
  int y;
  int width;
  int height;
  int maxDistance = 500;
  Player player;
  List<InterstellarObject> objects = new ArrayList<InterstellarObject>();
  
  // Calculated variables
  private int centerX;
  private int centerY;
  private int gridSpace;
  private int gridLines = 10;

  public Radar(Player player, List<InterstellarObject> objects) {
    this.player = player;
    this.objects = objects;
    
    init();
  }
  
  private void init() {
    centerX = x + width / 2;
    centerY = y + height / 2;
    gridSpace = width / gridLines / 2;
    
    System.out.println("center: (" + centerX + ", " + centerY + ")");
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
  
  private void drawGrid(Graphics g) {
    g.setColor(Tactical.basicColor);
    
    g.fillOval(centerX - 2, centerY - 2, 4, 4);
    for (int i = 0; i < 11; i++) {
      g.drawOval(centerX - gridSpace * i, centerY - gridSpace * i, gridSpace * i * 2, gridSpace * i * 2);
    }
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.darkGray);
    // g.fillRect(x, y, width, height);
    drawGrid(g);
    
    g.setColor(Color.red);
    for (InterstellarObject o: objects) {
      float distance = Distance.calculate(player, o);
      
      if (distance < maxDistance) {
        float radarDistance = (distance / maxDistance) * (height / 2);
        g.fillOval(centerX, (int) (centerY - radarDistance), 4, 4);
      }
    }
  }
}
