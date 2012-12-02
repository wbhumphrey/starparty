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
  InterstellarObject o;

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void setInterstellarObject(InterstellarObject o) {
    this.o = o;
  }

  public void draw(Graphics g) {
    if (o != null) {
      g.drawImage(o.portrait, x, y);

      g.setColor(Tactical.basicColor);
      g.setFont(Tactical.basicFont);
      g.drawString(o.name, x + 150, y);

      g.setFont(Tactical.smallFont);
      g.drawString(o.type, x + 150, y + 30);

      if (o instanceof Ship) {
        Ship s = (Ship) o;
        g.drawString((int)Math.ceil(s.shieldStrength) + " / " + s.maxShieldStrength + " Shield Strength", x + 150, y + 60);
        g.drawString((int)Math.ceil(s.hullStrength) + " / " + s.maxHullStrength + " Hull Strength", x + 150, y + 75);
      }
    }
  }
}
