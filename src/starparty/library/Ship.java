package starparty.library;

public class Ship extends InterstellarObject {
  private int maxShieldStrength;
  private int maxHullStrength;
  public double shieldStrength;
  public double hullStrength;

  public Ship(String name, float x, float y, float z) {
    super(name, x, y, z);
  }
  
  public Ship(){
  }
  
  @Override
  public boolean isDestroyed(){
    return hullStrength <= 0;
  }

  @Override
  public void recieveShieldDamage(double damage) {
    shieldStrength = Math.max(shieldStrength - damage, 0);
  }
  
  @Override
  public void recieveHullDamage(double damage, double shieldDamageReduction) {
    double damageReduction = damage * shieldDamageReduction * ( shieldStrength / maxShieldStrength);
    double damageDelt = Math.max( damage - damageReduction, 0 );
    hullStrength = Math.max( hullStrength - damageDelt, 0);
  }
  
  /* Getters and setters */

  public int getMaxShieldStrength() {
    return maxShieldStrength;
  }

  public void setMaxShieldStrength(int maxShieldStrength) {
    this.maxShieldStrength = maxShieldStrength;
  }

  public int getMaxHullStrength() {
    return maxHullStrength;
  }

  public void setMaxHullStrength(int maxHullStrength) {
    this.maxHullStrength = maxHullStrength;
  }

}
