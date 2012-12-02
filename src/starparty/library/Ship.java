package starparty.library;

public class Ship extends InterstellarObject {
  public int maxShieldStrength;
  public int maxHullStrength;
  public double shieldStrength;
  public double hullStrength;

  public Ship(String name, float x, float y, float z) {
    super(name, x, y, z);
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
}
