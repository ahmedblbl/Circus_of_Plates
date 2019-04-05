package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.cs19.world.Circus;
import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.cs19.world.Observer;
import eg.edu.alexu.csd.oop.cs19.world.Subject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Score implements Observer {

	private static Subject gameData1;
	private static int score;
	private static int lives;
	private static int leftHeight;
	private static int rightHeight;
	private List<GameObject> leftS;
	private List<GameObject> rightS;
	private CareTaker careTaker;
	private Orginator orginator = new Orginator();
	private int rightSize = 0;
	private int leftSize = 0;
	private boolean checkWin = false;
	private static Logger log;
	private List<GameObject> move;

	public Score(Subject gameData, CareTaker careTaker, List<GameObject> move) {
		
		log = Logger.getLogger(Score.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.INFO);
		
		this.move = move;
		gameData1 = gameData;
		this.careTaker = careTaker;
		gameData1.registerObserver(this);
		score = 0;
		lives = 3;
		leftHeight = 0;
		rightHeight = 0;
		
	}

	@Override
	public void update(List<GameObject> leftStick, List<GameObject> rightStick, List<GameObject> control) {
		
		if (rightSize != rightStick.size()) {
			rightHeight += check(rightStick, control);

			if (checkWin) {
				orginator.setHeight(rightHeight, leftHeight);
				careTaker.add(orginator.getMemento());

			}
		}

		if (leftSize != leftStick.size()) {
			leftHeight += check(leftStick, control);
			if (checkWin) {
				orginator.setHeight(rightHeight, leftHeight);
				careTaker.add(orginator.getMemento());
			}
		}
		checkWin = false;
		leftS = leftStick;
		rightS = rightStick;
		leftSize = leftStick.size();
		rightSize = rightStick.size();
		
	}

	private int check(List<GameObject> stick, List<GameObject> control) {

		int i = stick.size() - 1;
		if (i < 0) {
			return 0;
		}
		int height = stick.get(i).getHeight();
		if (i >= 2) {
			if (matches(stick.get(i), stick.get(i - 1), stick.get(i - 2))) {
				
				log.info("The user collected three consecutive identical colors.");
				
				score += 10;
				height = height - stick.get(i).getHeight() - stick.get(i - 1).getHeight()
						- stick.get(i - 2).getHeight();

				log.info("Reusing the collected identical colored plates ...");
				
				GameObject temp = stick.remove(i);
				GameObject temp1 = stick.remove(i - 1);
				GameObject temp2 = stick.remove(i - 2);
				Random r = new Random();
				control.remove(temp);
				control.remove(temp1);
				control.remove(temp2);
				((ColorDecorator) temp).setState(new MovableObject(r.nextInt(1500) + 100, -1 * r.nextInt(20000)));
				((ColorDecorator) temp1).setState(new MovableObject(r.nextInt(1500) + 100, -1 * r.nextInt(20000)));
				((ColorDecorator) temp2).setState(new MovableObject(r.nextInt(1500) + 100, -1 * r.nextInt(20000)));
				move.add(temp);
				move.add(temp1);
				move.add(temp2);
				
				orginator.setState(leftS.size(), rightS.size());
				checkWin = true;
				AudioInputStream audioIn;
				try {
					audioIn = AudioSystem.getAudioInputStream(new File("applause.wav"));
					// Get a sound clip resource.
					Clip clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					clip.start();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					log.error("Couldn't play the applause soundeffect !!!");
				}

			}
		}
		return height;
	}

	private boolean matches(GameObject gameObject, GameObject gameObject2, GameObject gameObject3) {
		// TODO Auto-generated method stub

		return (gameObject.getClass().equals(gameObject2.getClass())
				&& gameObject2.getClass().equals(gameObject3.getClass()));
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLeftHeight() {
		return leftHeight;
	}

	public void setLeftHeight(int leftHeight) {
		this.leftHeight = leftHeight;
	}

	public int getRightHeight() {

		return rightHeight;
	}

	public void setRightHeight(int rightHeight) {
		this.rightHeight = rightHeight;
	}

	public void setRightS(int s) {
		rightSize = s;
	}

	public void setLeftS(int s) {
		leftSize = s;
	}
}
