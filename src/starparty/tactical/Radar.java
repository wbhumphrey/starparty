/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.ArrayList;
import java.util.Collection;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import starparty.library.InterstellarObject;
import starparty.library.Weapon;
import starparty.library.WeaponRange;
import starparty.utilities.Distance;

/**
 *
 * @author Tyler
 */
public class Radar implements TargetListener {
  int x;
  int y;
  int width;
  int height;
  
  InterstellarObject source;
  Collection<InterstellarObject> objects = new ArrayList<InterstellarObject>();
  Target target;
  Image weaponRanges;
  
  // Calculated variables
  int maxDistance = 500;
  private final int OBJECT_POINT_SIZE = 6;
  private int centerX;
  private int centerY;
  private int gridSpace;
  private int gridLines = 8;
  
  final Color HEAVY_DAMAGE_COLOR = new Color(50, 0, 0);
  final Color MEDIUM_DAMAGE_COLOR = new Color(50, 50, 0);
  final Color LIGHT_DAMAGE_COLOR = new Color(0, 50, 0);

  public Radar(InterstellarObject currentLocation, Collection<InterstellarObject> objects, Target target) {
    this.source = currentLocation;
    this.objects = objects;
    this.target = target;
    target.addTargetListener(this);
    
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
 
  
  
  private void drawWeaponRangesImage(){
    try {
      Weapon weapon = target.getWeapon();

      weaponRanges = new Image(width, height);

      for(WeaponRange r: weapon.ranges) {
        Image weaponRange = new Image(width, height);
        Graphics buffer = weaponRange.getGraphics();

        if (r.percentDamage < .3)
          buffer.setColor(LIGHT_DAMAGE_COLOR);
        else if (r.percentDamage < .6)
          buffer.setColor(MEDIUM_DAMAGE_COLOR);
        else
          buffer.setColor(HEAVY_DAMAGE_COLOR);

        int radius = (int) (((0.0 + r.max) / maxDistance) * (width / 2));
        int x = (width / 2) - radius;
        int y = (width / 2) - radius;

        buffer.fillOval(x, y, radius * 2, radius * 2);

        if (r.min > 0) {
          buffer.setDrawMode(Graphics.MODE_ALPHA_MAP);
          buffer.setColor(Color.transparent);
          radius = (int) (((0.0 + r.min) / maxDistance) * (width / 2));
          x = (width / 2) - radius;
          y = (width / 2) - radius;

          buffer.fillOval(x, y, radius * 2, radius * 2);
          buffer.setDrawMode(Graphics.MODE_NORMAL);
        }

        buffer.flush();
        weaponRanges.getGraphics().drawImage(weaponRange, 0, 0);
      }
      
      weaponRanges.getGraphics().flush();
    } catch (Exception e) {
      e.printStackTrace();
    }    
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.darkGray);
    // g.fillRect(x, y, width, height);
    
    if (weaponRanges != null) {
      g.drawImage(weaponRanges, x, y);
    }
    
    drawGrid(g);
    
    g.setColor(Color.red);
    for (InterstellarObject o: objects) {
      double distance = Distance.calculate(source, o);
      int direction = o.hash(360);
      
      if (distance < maxDistance && !o.isDestroyed()) {
        double radarDistance = (distance / maxDistance) * (height / 2);
        double distanceX = Math.cos(Math.toRadians(direction)) * radarDistance;
        double distanceY = Math.sin(Math.toRadians(direction)) * radarDistance;
        
        if (o == target.getTarget()) {
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
      double distance = Distance.calculate(source, o);
      int direction = o.hash(360);
      
      if (distance < maxDistance) {
        double radarDistance = (distance / maxDistance) * (height / 2);
        double distanceX = Math.cos(Math.toRadians(direction)) * radarDistance;
        double distanceY = Math.sin(Math.toRadians(direction)) * radarDistance;
        
        c.setLocation((int) (centerX - distanceX), (int) (centerY - distanceY));
        if (c.contains(x, y)) {
          target.setTarget(o);
          return true;
        }
      }
    }
    
    return false;
  }

  @Override
  public void targetChanged(Target target) {
    if(target.getWeapon() != target.getOldWeapon()){
      drawWeaponRangesImage();  
    }
  }
}
