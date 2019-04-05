package eg.edu.alexu.csd.oop.cs19.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.cs19.logic.Background;
import eg.edu.alexu.csd.oop.cs19.logic.Board;
import eg.edu.alexu.csd.oop.cs19.logic.Context;
import eg.edu.alexu.csd.oop.cs19.logic.HelpingMove;
import eg.edu.alexu.csd.oop.cs19.logic.Iterator;
import eg.edu.alexu.csd.oop.cs19.logic.JarReader;
import eg.edu.alexu.csd.oop.cs19.logic.LeaderBoard;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus implements World {

	private final int width;
	private final int height;
	private final List<GameObject> constant;
	private final List<GameObject> moving;
	private final List<GameObject> control;
	private Factory factory;
	private HelpingMove help;
	private int x;
	private JLabel scoreLbl;
	private JLabel livesLbl;
	private JLabel lvlLabel;
	private JarReader rReader;
	private HashMap<String, BufferedImage> resources;
	private static Logger log;
	private boolean lost = true;
	private Context context;
	private int checkClown;

	
	public Circus(int screenWidth, int screenHeight, Factory factory, IStateLevel stateDifficulty, Context context, JLabel scoreLbl, JLabel livesLbl,JLabel lvlLabel) {
		
		log = Logger.getLogger(Circus.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.INFO);
		
		log.info("Building the circus scene ...");
		
		this.scoreLbl = scoreLbl;
		this.livesLbl = livesLbl;
		this.context = context;
		this.context.setState(stateDifficulty);
		this.factory = factory;
		this.width = screenWidth;
		this.height = screenHeight;
		this.lvlLabel = lvlLabel;
		this.constant = new LinkedList<GameObject>();
		this.moving = new LinkedList<GameObject>();
		this.control = new LinkedList<GameObject>();
		this.help = new HelpingMove(constant, moving, control);
		x = 0;
		this.rReader = new JarReader();
		this.resources = new HashMap<>();
		
		String[] temp = getClass().getCanonicalName().split(getClass().getPackage().getName());
		File file = new File(temp[0]);
		System.out.println("Package name: " + file.getAbsolutePath());
		try {
			log.info("Loading Essential images from the jar file ...");
			this.resources = rReader.getResFromJar(file.getAbsolutePath() + "\\LibImages.jar");
		} catch (Exception e) {
			log.fatal("Couldn't load the images from the jar file !!!");
		}
		
		BufferedImage clown = this.resources.get("clownChar");
		BufferedImage background = this.resources.get("background");
		BufferedImage left = this.resources.get("left");
		BufferedImage right = this.resources.get("right");
		
		this.constant.add(new Background(0, 0, background));
		this.control.add(
				factory.createObject("STICK", 20, this.height - 210, left));
		this.control.add(factory.createObject("STICK", 182, this.height - 210, right));
		this.control
				.add(factory.createObject("PLAYER", 62, this.height - 200, clown));
		checkClown = 62;

		this.context.getState().moveChange(this.moving, this.factory);
		
		log.info("The circus scene was built successfully ...");

	}

	@Override
	public List<GameObject> getConstantObjects() {
		// TODO Auto-generated method stub
		return this.constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		// TODO Auto-generated method stub
		return this.moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		// TODO Auto-generated method stub
		return this.control;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public boolean refresh() {

		moveToLimit();
		this.checkClown = control.get(2).getX();
		context.getState().gameChange(moving, factory, help);
		scoreLbl.setText("score:   " + help.getScore());
		livesLbl.setText("lives:   " + help.getLives());
		lvlLabel.setText("level:   " + context.getState().getLvlName());

		if (Integer.parseInt(livesLbl.getText().split("lives:   ")[1]) == 0) {
			if (lost) {
				lost = false;
				log.info("The user Lost The Game.");
			}
			if (x == 0 && help.getScore() > 0) {
				log.info("Saving the user's score to the leaderboard ...");
				LeaderBoard board = new Board("Score", help.getScore());
				board.generateLeader();
				x++;
			}	
			return false;
		}
		
		return true;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return "score:   " + help.getScore() + "   ||   " + "lives:   " + help.getLives() + "   ||   " + "level:   " + context.getState().getLvlName();
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return 25;
	}

	public void moveToLimit() {

		if (this.control.get(2).getX() == checkClown) {

			this.control.get(0).setX(this.control.get(2).getX() - 42);
			this.control.get(1).setX(this.control.get(2).getX() + 122);

			objectsLimit();

		} else if (this.control.get(2).getX() < 62) {

			this.control.get(2).setX(62);
			this.control.get(0).setX(20);
			this.control.get(1).setX(182);
			objectsLimit();

		}

	}

	public void objectsLimit() {

		Iterator controlItr = new Iterator(control);
		controlItr.setCursor(2);
		while (controlItr.hasNext()) {
			GameObject g = controlItr.next();
			if (Math.abs(g.getX() - control.get(0).getX()) > Math.abs(g.getX() - control.get(1).getX())) {
				g.setX(control.get(1).getX() + (control.get(1).getWidth() * 2 / 3) - (g.getWidth() / 2));
			} else {
				g.setX(control.get(0).getX() + (control.get(0).getWidth() / 3) - (g.getWidth() / 2));
			}
		}

	}

}
