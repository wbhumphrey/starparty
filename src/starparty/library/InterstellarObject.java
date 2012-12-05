package starparty.library;

import javax.vecmath.Point3d;
import org.newdawn.slick.Image;

public class InterstellarObject {

  public String name;
  public String type;
  public String organization;
  private Point3d location = new Point3d();
  public Image portrait;
  public Image icon;

  public InterstellarObject(String name, double x, double y, double z) {
    this.name = name;
    setLocation(x, y, z);
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

  public Point3d getLocation() {
    return location;
  }

  final public void setLocation(double x, double y, double z) {
    location.x = x;
    location.y = y;
    location.z = z;
  }
  
}
