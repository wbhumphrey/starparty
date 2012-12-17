/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.pilot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.library.Ship;

/**
 *
 * @author Tyler
 */
public class Starfield {
  int height;
  int width;
  int x;
  int y;
  Ship ship;
  List<Star> stars = new ArrayList<Star>();
  
  public Starfield(Ship ship) {
    this.ship = ship;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public void init() {
    for (int i = 0; i < 150; i++) {
      Star star;
      stars.add(new Star());
    }
  }
  
  public void draw(Graphics g) {
    for (Star star: stars) {
      star.draw(this, ship, g);
    }
  }
}


class Star {
  private static Random r = new Random();
  int size;
  int x;
  int y;
  
  public Star() {
    int t = r.nextInt(100);
    
    if (t < 50)
      size = 1;
    else if (t < 80) {
      size = 2;
    } else {
      size = 3;
    }
    
    x = r.nextInt(1024);
    y = r.nextInt(700);
  }
  
  public void draw(Starfield s, Ship ship, Graphics g) {
    g.setColor(Color.white);
    
    int deltaX = -(((int) (ship.getLocation().x / (5.0 - size))) % s.width);
    int deltaY = (((int) (ship.getLocation().y / (5.0 - size))) % s.height);
    
    int drawX = ((x + deltaX) + s.width) % s.width;
    int drawY = ((y + deltaY) + s.height) % s.height;
    
    g.fillRect(drawX, drawY, size, size);
  }
}
