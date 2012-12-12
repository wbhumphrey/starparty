package starparty.library;

public class Player {

  public Ship ship;
  public boolean moving = false, mLeft = false, mRight = false, mUp = false, mDown = false;
  public float speed = 1, angle = 0;

  public Player(float x, float y) {
    ship = new Ship("USS Party", x, y);
  }
}
