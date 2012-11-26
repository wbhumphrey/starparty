package starparty.tactical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.*;
import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.library.Weapon;
import starparty.utilities.FontLoader;

public class Tactical extends BasicGame {
	List<InterstellarObject> interstellarObjects = new ArrayList<InterstellarObject>();
  List<Weapon> weapons = new ArrayList<Weapon>();
  Player player;
  Image background;
  
  Radar radar;
  WeaponManager weaponManager;
  
  // Global styles
  public static Color basicColor;
  public static UnicodeFont basicFont;
  
  public Tactical() {
		super("StarParty");
	}

	public static void main(String ... args){
		try {
			System.out.println(new java.io.File( "." ).getCanonicalPath());
      
			AppGameContainer app = new AppGameContainer(new Tactical());
			app.setDisplayMode(1024, 700, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
    g.drawImage(background, 0, 0);
    radar.draw(g);
    weaponManager.draw(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
    basicColor = new Color(100, 151, 244);
    basicFont = FontLoader.load("TCM_____.TTF", 25);
    
    Player player = new Player(0, 0, 0);
    
		Random r = new Random();
		for(int i = 1; i <= 20; i++){
			interstellarObjects.add(new InterstellarObject(r.nextFloat() * 800 - 400, r.nextFloat() * 800 - 400, r.nextFloat() * 800 - 400));
		}
    
    radar = new Radar(player, interstellarObjects);
    radar.setLocation(519, 71);
    radar.setSize(500, 500);
    
    weapons.add(new Weapon("Phaser"));
    weapons.add(new Weapon("Photon Torpedos"));
    weapons.add(new Weapon("Laser"));
    weapons.add(new Weapon("Qauntum Torpedo"));
    weapons.add(new Weapon("Planet Buster"));
    weapons.add(new Weapon("Machine Gun"));
    
    weaponManager = new WeaponManager(weapons);
    weaponManager.setLocation(150, 90);
    weaponManager.setSize(180, 600);
    
    background = new Image("resources/tactical/background.jpg");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {

	}

  @Override
  public void mousePressed(int button, int x, int y) {
    super.mousePressed(button, x, y);
  }
}
