/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.library.Weapon;
import starparty.library.WeaponRange;
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
  
  Player player;
  List<InterstellarObject> objects = new ArrayList<InterstellarObject>();
  WeaponManager weaponManager;
  Image weaponRangeImage;
  
  // Calculated variables
  int maxDistance = 500;
  private final int OBJECT_POINT_SIZE = 6;
  private int centerX;
  private int centerY;
  private int gridSpace;
  private int gridLines = 8;

  public Radar(Player player, List<InterstellarObject> objects, WeaponManager weaponManager) {
    this.player = player;
    this.objects = objects;
    this.weaponManager = weaponManager;
    
    init();
  }
  
  private void init() {
    centerX = x + width / 2;
    centerY = y + height / 2;
    gridSpace = width / gridLines / 2;
    
    // System.out.println("center: (" + centerX + ", " + centerY + ")");
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
    for (int i = 0; i < gridLines + 1; i++) {
      g.drawOval(centerX - gridSpace * i, centerY - gridSpace * i, gridSpace * i * 2, gridSpace * i * 2);
    }
  }
  
  public void setWeaponRangeImage(Image weaponRangeImage) {
    this.weaponRangeImage = weaponRangeImage;
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.darkGray);
    // g.fillRect(x, y, width, height);
    
    if (weaponManager.selectedWeapon != null) {
      g.drawImage(weaponRangeImage, x, y);
    }
    
    drawGrid(g);
    
    g.setColor(Color.red);
    for (InterstellarObject o: objects) {
      double distance = Distance.calculate(player, o);
      int direction = o.hash(360);
      
      if (distance < maxDistance) {
        double radarDistance = (distance / maxDistance) * (height / 2);
        double distanceX = Math.cos(Math.toRadians(direction)) * radarDistance;
        double distanceY = Math.sin(Math.toRadians(direction)) * radarDistance;
        // System.out.println("(" + distanceX + ", " + distanceY + ")");
        // System.exit(0);
        g.fillOval((int) (centerX - distanceX) - (OBJECT_POINT_SIZE / 2), (int) (centerY - distanceY) - (OBJECT_POINT_SIZE / 2), OBJECT_POINT_SIZE, OBJECT_POINT_SIZE);
      }
    }
  }
}
