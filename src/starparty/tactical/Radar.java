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
import org.newdawn.slick.geom.Circle;
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
  
  Player player;
  List<InterstellarObject> objects = new ArrayList<InterstellarObject>();
  WeaponManager weaponManager;
  InterstellarObjectStatus objectStatus;
  InterstellarObject selectedObject;
  Image weaponRangeImage;
  
  // Calculated variables
  int maxDistance = 500;
  private final int OBJECT_POINT_SIZE = 6;
  private int centerX;
  private int centerY;
  private int gridSpace;
  private int gridLines = 8;

  public Radar(Player player, List<InterstellarObject> objects) {
    this.player = player;
    this.objects = objects;
    
    init();
  }
  
  public void setWeaponManager(WeaponManager weaponManager) {
    this.weaponManager = weaponManager;
  }
  
  public void setInterstellarObjectStatus(InterstellarObjectStatus objectStatus) {
    this.objectStatus = objectStatus;
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
        
        if (o == selectedObject) {
          g.setColor(Color.yellow);
          g.fillOval((int) (centerX - distanceX) - 14, (int) (centerY - distanceY) - 14, 28, 28);
          g.setColor(Color.red);
        }
        
        g.drawImage(o.icon, (int) (centerX - distanceX) - 12, (int) (centerY - distanceY) - 12);
        // g.fillOval((int) (centerX - distanceX) - (OBJECT_POINT_SIZE / 2), (int) (centerY - distanceY) - (OBJECT_POINT_SIZE / 2), OBJECT_POINT_SIZE, OBJECT_POINT_SIZE);
      }
    }
  }
  
  public boolean click(int x, int y) {
    Circle c = new Circle(0, 0, 12);
    
    for (InterstellarObject o: objects) {
      double distance = Distance.calculate(player, o);
      int direction = o.hash(360);
      
      if (distance < maxDistance) {
        double radarDistance = (distance / maxDistance) * (height / 2);
        double distanceX = Math.cos(Math.toRadians(direction)) * radarDistance;
        double distanceY = Math.sin(Math.toRadians(direction)) * radarDistance;
        
        c.setLocation((int) (centerX - distanceX), (int) (centerY - distanceY));
        if (c.contains(x, y)) {
          selectedObject = o;
          objectStatus.setInterstellarObject(o);
          return true;
        }
      }
    }
    
    return false;
  }
}
