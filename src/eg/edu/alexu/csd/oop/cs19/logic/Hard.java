package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Hard implements IStateLevel {
	private int numBombs = 0;
	private int numSkulls = 0;
	private int numGifts = 0;
	private int numLives = 0;
	private final int MAX_MOVING = 300;
	private static Logger log;
	private Context context;
	
	public Hard(Context context) {
		this.context = context;
		log = Logger.getLogger(Hard.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.INFO);
	}

	@Override
	public void moveChange(List<GameObject> moving, Factory factory) {

		Random r = new Random();
		for (int i = 0; i < MAX_MOVING; i++) {
			moving.add(factory.createObject("Shape", r.nextInt(1500) + 100, -1 * r.nextInt(20000), null));
		}

	}

	@Override
	public void gameChange(List<GameObject> move, Factory factory, HelpingMove help) {

		numBombs++;
		numSkulls++;
		numGifts++;
		numLives++;
		Random r = new Random();
		
		Iterator movingItr = new Iterator(move); 
		while (movingItr.hasNext()) {
			GameObject g = movingItr.next();
			g.setY(g.getY() + 10);
			if (g.getY() > 1000) {
				log.info("Reusing the shape out of screen ...");
				g.setX(r.nextInt(1500) + 100);
				g.setY(-1 * r.nextInt(20000));
			}
		}

		help.setDifference(10);

		movingItr.setCursor(-1);
		while (movingItr.hasNext()) {
			if (help.isIntersect(movingItr.next(), movingItr.getCursor())) {
				movingItr.previous();
			}
		}

		if (numBombs > 2000) {
			numBombs = 0;
			move.add(factory.createObject("bomb", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}
		
		if (numSkulls > 600) {
			numSkulls = 0;
			move.add(factory.createObject("skull", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}

		if (numGifts > 2500) {
			numGifts = 0;
			move.add(factory.createObject("gift", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}
		
		if (numLives > 9000) {
			numLives = 0;
			move.add(factory.createObject("live", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}

	}

	@Override
	public String getLvlName() {
		return "Hard";
	}
}
