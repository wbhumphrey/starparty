package starparty.library;

import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import org.newdawn.slick.Image;

public class InterstellarObject {

  public String name;
  public String type;
  public String organization;
  private Point2d location = new Point2d();
  public Image portrait;
  public Image icon;

  public InterstellarObject(String name, double x, double y) {
    this.name = name;
    setLocation(x, y);
  }

  public InterstellarObject() {
  }

  public int hash(int mod) {
    return Math.abs(name.hashCode() % mod);
  }

  public void recieveShieldDamage(double damage) {
  }

  public void recieveHullDamage(double damage, double shieldDamageReduction) {
  }

  public boolean isDestroyed() {
    return false;
  }

  public Point2d getLocation() {
    return location;
  }

  final public void setLocation(double x, double y) {
    location.x = x;
    location.y = y;
  }
  
}
