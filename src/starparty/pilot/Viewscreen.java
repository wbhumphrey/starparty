package starparty.pilot;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.vecmath.Point3d;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Path;

import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.utilities.Distance;

public class Viewscreen {

  int x, y, height, width;
  double gridAngle;
  boolean xy;
  List<InterstellarObject> interstellarObjects;
  Player player;
  private Color alphaWhiteColor = new Color(255, 255, 255, 50);
  // Image buffers
  Image view;

  public Viewscreen(Player player, int x, int y, int height, int width, boolean xy, List<InterstellarObject> interstellarObjects) {
    this.gridAngle = Math.toRadians(80);
    this.player = player;
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    this.interstellarObjects = interstellarObjects;
    this.xy = xy;
    try {
      view = new Image(width, height);
    } catch (SlickException ex) {
      Logger.getLogger(Viewscreen.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void drawGrid(Graphics g) {
    try {
      Graphics buffer = view.getGraphics();
      buffer.clear();
      buffer.setColor(Color.green);
      buffer.drawLine(0, 0, width, 0);
      buffer.drawLine(0, 0, 0, height);
      buffer.drawLine(width, 0, width, height);
      buffer.drawLine(0, 0 + height, width, height);
      buffer.drawLine(width / 2, 0, width / 2, height);
      buffer.drawLine(0, height / 2, width, height / 2);
      buffer.drawString("(0, 0)", x + width / 2, y + height / 2);
      buffer.rotate(0, 0, 45);
      buffer.flush();
      g.drawImage(view, x, y);
    } catch (SlickException ex) {
      Logger.getLogger(Viewscreen.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void drawGrid2(Graphics g) {
    g.setColor(Color.green);
    g.drawLine(x, y, x + width, y);
    g.drawLine(x, y, x, y + height);
    g.drawLine(x + width, y, x + width, y + height);
    g.drawLine(x, y + height, x + width, y + height);
    g.drawLine(x + width / 2, y, x + width / 2, y + height);
    g.drawLine(x, y + height / 2, x + width, y + height / 2);
    g.drawString("(0, 0)", x + width / 2, y + height / 2);
  }

  private void drawInterstellarObjects(Graphics g) {
    int range = 200;
    for (InterstellarObject interstellarObject : interstellarObjects) {
      if(Distance.isDistanceGreaterThan(player.ship, interstellarObject, range)) {
        continue;
      }
      Point3d location = player.ship.getLocation();
      Point3d iLocation = interstellarObject.getLocation();
      if (iLocation.z > location.z + 50) {
        g.setColor(Color.pink);
      } else if (iLocation.z < location.z - 50) {
        g.setColor(Color.magenta);
      } else {
        g.setColor(Color.red);
      }
      float centerX = x + width / 2, centerY = y + height / 2;
      if (xy) {
        g.fillOval((float) (location.x + centerX + iLocation.x), (float) (location.y + centerY + iLocation.y), 6, 6);
      } else {
        g.fillOval((float) (location.y + centerX + iLocation.y), (float) (location.z + centerY + iLocation.z), 6, 6);
      }
      //System.out.println("(" + interstellarObject.x + ", " + interstellarObject.y + ")");
      //g.drawRect(x + interstellarObject.x, interstellarObject.y, 2, 2);

    }
  }

  public void draw(Graphics g) {
    g.setAntiAlias(true);
    drawInterstellarObjects(g);
    drawGrid(g);

    float centerX = x + width / 2, centerY = y + height / 2;
    Path path = new Path(centerX, centerY);
    path.curveTo(centerX + 190, centerY + 140, centerX, centerY, centerX + 50, centerY + 100);

    g.setColor(alphaWhiteColor);
    g.fill(path);
    g.setColor(Color.white);
    g.draw(path);
  }
}