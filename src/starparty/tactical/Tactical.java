package starparty.tactical;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.newdawn.slick.*;
import starparty.library.*;
import starparty.utilities.FontLoader;
import starparty.utilities.InterstellarObjectRepository;

public class Tactical extends BasicGame {

  Collection<InterstellarObject> interstellarObjects = new ArrayList<InterstellarObject>();
  List<Weapon> weapons = new ArrayList<Weapon>();
  Player player;
  Image background;
  Radar radar;
  WeaponManager weaponManager;
  FiringControls firingControls;
  InterstellarObjectStatus objectStatus;
  
  Target target = new Target();
  // Global styles
  public static Color basicColor;
  public static Color backgroundColor;
  public static UnicodeFont titleFont;
  public static UnicodeFont basicFont;
  public static UnicodeFont smallFont;

  public Tactical() {
    super("StarParty");
  }

  public static void main(String... args) {
    try {
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
    firingControls.draw(g);
    objectStatus.draw(g);
  }

  @Override
  public void init(GameContainer gc) throws SlickException {
    gc.getGraphics().setAntiAlias(true);

    basicColor = new Color(100, 151, 244);
    backgroundColor = Color.black;

    basicFont = FontLoader.load("TCM_____.TTF", 30);
    titleFont = FontLoader.load("TCM_____.TTF", 35, true);
    smallFont = FontLoader.load("TCM_____.TTF", 15);
    
    InterstellarObjectRepository repo = InterstellarObjectRepository.initialize(
            "resources/interstellar_object/interstellar_object_schematic.json");

    player = new Player(0, 0, 0);
    player.ship = repo.getShip("Domenica");
    player.ship.setLocation(0, 0, 0);
            
    interstellarObjects = repo.values();
    interstellarObjects.remove(player.ship);
    
//    Weapon w;
//    weapons.add(w = new Weapon("Phaser"));
//    w.shieldDamage = 100;
//    w.hullDamage = 0;
//    w.addRange(new WeaponRange(0, 100, 1));
//    w.addRange(new WeaponRange(101, 200, .5));
//    w.addRange(new WeaponRange(201, 500, .25));
//
//    weapons.add(w = new Weapon("Photon Torpedos"));
//    w.shieldDamage = 25;
//    w.hullDamage = 100;
//    w.shieldDamageReduction = .5;
//    w.addRange(new WeaponRange(300, 500, 1));
//
//    weapons.add(w = new Weapon("Rail Gun"));
//    w.shieldDamage = 10;
//    w.hullDamage = 50;
//    w.shieldDamageReduction = 1;
//    w.addRange(new WeaponRange(0, 500, 1));
//    w.addRange(new WeaponRange(501, 1000, .5));

    firingControls = new FiringControls(player.ship, target);
    firingControls.setLocation(107, 473);
    firingControls.setSize(450, 214);

    //weapons.add(new Weapon("Laser"));
    //weapons.add(new Weapon("Quantum Torpedo"));
    //weapons.add(new Weapon("Planet Buster"));
    //weapons.add(new Weapon("Machine Gun"));

    weaponManager = new WeaponManager(player.ship.weapons);
    weaponManager.setLocation(120, 100);
    weaponManager.setSize(300, 600);
    weaponManager.setFiringControls(firingControls);

    objectStatus = new InterstellarObjectStatus();
    objectStatus.setLocation(608, 540);
    objectStatus.setSize(400, 146);

    radar = new Radar(player, interstellarObjects, target);
    radar.setWeaponManager(weaponManager);
    radar.setInterstellarObjectStatus(objectStatus);
    radar.setLocation(608, 96);
    radar.setSize(400, 400);
    weaponManager.setRadar(radar);

    background = new Image("resources/tactical/background.jpg");
  }

  @Override
  public void update(GameContainer gc, int delta) throws SlickException {
  }

  @Override
  public void keyPressed(int key, char c) {
    player.ship.getLocation().x += 20;
  }

  @Override
  public void mousePressed(int button, int x, int y) {
    weaponManager.click(x, y);
    firingControls.click(x, y);
    radar.click(x, y);
  }
}
