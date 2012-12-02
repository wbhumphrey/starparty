package starparty.library;

import org.newdawn.slick.Image;

public class InterstellarObject {

  public String name;
  public String type;
  public String organization;
  public float x, y, z;
  public Image portrait;
  public Image icon;

  public InterstellarObject(String name, float x, float y, float z) {
    this.name = name;

    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void setLocation(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
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
}
