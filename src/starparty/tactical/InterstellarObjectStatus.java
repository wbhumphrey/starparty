/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import org.newdawn.slick.Graphics;
import starparty.library.InterstellarObject;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class InterstellarObjectStatus {

  int x;
  int y;
  int width;
  int height;
  
  Target target;

  public InterstellarObjectStatus(Target target) {
    this.target = target;
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
    InterstellarObject o = target.getTarget();
    if (o != null) {
      g.drawImage(o.portrait, x, y);

      g.setColor(Tactical.basicColor);
      g.setFont(Tactical.basicFont);
      g.drawString(o.name, x + 150, y);

      g.setFont(Tactical.smallFont);
      g.drawString(o.type, x + 150, y + 30);

      if (o instanceof Ship) {
        Ship s = (Ship) o;
        g.drawString((int)Math.ceil(s.shieldStrength) + " / " + s.getMaxShieldStrength() + " Shield Strength", x + 150, y + 60);
        g.drawString((int)Math.ceil(s.hullStrength) + " / " + s.getMaxHullStrength() + " Hull Strength", x + 150, y + 75);
      }
    }
  }
}
