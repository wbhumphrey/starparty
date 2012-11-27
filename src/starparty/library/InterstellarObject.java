package starparty.library;

public class InterstellarObject {
  String name;         
	public float x, y, z;

	public InterstellarObject(String name, float x, float y, float z) {
    this.name = name;
    
		this.x = x;
		this.y = y;
		this.z = z;
  }
  
  public int hash(int mod) {
    return Math.abs(name.hashCode() % mod);
  }
}
