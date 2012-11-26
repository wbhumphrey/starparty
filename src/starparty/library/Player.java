package starparty.library;

public class Player extends Ship {
	
	public boolean moving = false, mLeft = false, mRight = false, mUp = false, mDown = false;
	public float speed = 1, angle = 0;
	
	public Player(float x, float y, float z) {
		super(x, y, z);
	}
}
