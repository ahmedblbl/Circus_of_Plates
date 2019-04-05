package eg.edu.alexu.csd.oop.cs19.logic;


import java.util.List;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Easy implements IStateLevel{
	private int numBombs = 0;
	private final int MAX_MOVING = 200;
	private static Logger log;
	private Context context;
	
	public Easy(Context context) {
		
		this.context = context;
		
		log = Logger.getLogger(Easy.class.getName());
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
		Random r = new Random();
		
		

		Iterator movingItr = new Iterator(move);
		while (movingItr.hasNext()) {
			GameObject g = movingItr.next();
			g.setY(g.getY() + 2);
			if(g.getY() > 1000) {
				log.info("Reusing the shape out of screen ...");
				g.setX(r.nextInt(1500) + 100);
				g.setY( -1 * r.nextInt(20000));
			}
			
		}

		help.setDifference(2);
		
		movingItr.setCursor(-1);
		while (movingItr.hasNext()) {
			if (help.isIntersect(movingItr.next(), movingItr.getCursor())) {
				movingItr.previous();
			}
		}
		
		if(numBombs > 700) {
			numBombs = 0;
			move.add(factory.createObject("bomb", r.nextInt(1500) + 100, -1 * r.nextInt(20000), null));
		}
		
		if (help.getScore() >= 75) {
			log.info("The user is advanced to the next level (Medium)");
			this.context.setState(new Medium(this.context));
		}
		
	}

	@Override
	public String getLvlName() {
		return "Easy";
	}

}
