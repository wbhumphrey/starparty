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
//    g.drawImage(background, 0, 0);
//    g.setColor(basicColor);
//    g.fillArc(100, 100, 100, 100, 0, 90);
//    g.fillArc(100, 100, 100, 100, 120, 240);
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

    player = new Player(0, 0);
    player.ship = repo.getShip("Domenica");
    player.ship.setLocation(0, 0);
            
    interstellarObjects = repo.values();
    interstellarObjects.remove(player.ship);

    firingControls = new FiringControls(player.ship, target);
    firingControls.setLocation(107, 473);
    firingControls.setSize(450, 214);

    weaponManager = new WeaponManager(player.ship.weapons, target);
    weaponManager.setLocation(120, 100);
    weaponManager.setSize(300, 600);
    weaponManager.setFiringControls(firingControls);

    objectStatus = new InterstellarObjectStatus(target);
    objectStatus.setLocation(608, 540);
    objectStatus.setSize(400, 146);

    radar = new Radar(player.ship, interstellarObjects, target);
    radar.setLocation(608, 96);
    radar.setSize(400, 400);

    background = new Image("resources/tactical/background.jpg");
  }

  @Override
  public void update(GameContainer gc, int delta) throws SlickException {
  }

  @Override
  public void keyPressed(int key, char c) {
//    player.ship.getLocation().x += 250;
    player.ship.direction += Math.PI / 4;
  }

  @Override
  public void mousePressed(int button, int x, int y) {
    weaponManager.click(x, y);
    firingControls.click(x, y);
    radar.click(x, y);
  }
}
