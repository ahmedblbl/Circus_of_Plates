package eg.edu.alexu.csd.oop.cs19.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import eg.edu.alexu.csd.oop.game.GameObject;

public class HelpingMove {
	private static List<GameObject> constant;
	private static List<GameObject> moving;
	private static List<GameObject> control;
	private static List<GameObject> leftStickObject;
	private static List<GameObject> rightStickObject;
	private static GameData gameData;
	private Orginator orginator;
	private CareTaker careTaker;
	private Memento memento;
	private static Score score;
	private int difference;
	private static int lives;
	private static Logger log;

	public HelpingMove(List<GameObject> constant1,List<GameObject> move,List<GameObject> control1) {

		log = Logger.getLogger(HelpingMove.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.INFO);
		
		lives = 3;
		moving = move;
		constant = constant1;
		control = control1;
		careTaker = new CareTaker();
		orginator = new Orginator();
		rightStickObject = new LinkedList<GameObject>();
		leftStickObject = new LinkedList<GameObject>();
		gameData = new GameData(leftStickObject, rightStickObject,control);
		score = new Score(gameData, careTaker, moving);

	}
	
	public void setDifference(int difference) {
		this.difference = difference;
	}
	
	public boolean isIntersect(GameObject fallingObject, int index) {
		boolean right = false;
		boolean left = false;
		int xFall = fallingObject.getX();
		int yFall = fallingObject.getY() + fallingObject.getHeight();
		GameObject leftStick = control.get(0);
		GameObject rightStick = control.get(1);
		
		if ((leftStick.getY() - score.getLeftHeight()) - yFall <= this.difference && yFall <= (leftStick.getY() - score.getLeftHeight())) {
			
			if ((leftStick.getX() >= xFall && leftStick.getX() <= xFall + fallingObject.getWidth())  	||     (leftStick.getX() + (leftStick.getWidth() * 2 / 3) >= xFall && leftStick.getX() + leftStick.getWidth() * 2 / 3 <= xFall + fallingObject.getWidth())) {

				log.info("An intersect took place on the left stick.");
				
				if (fallingObject.getClass().toString().contains("SkullObject")) {
					log.info("The user collected a skull.");
					lives--;
					score.setLives(lives);
					moving.remove(index);
					return true;
				}
				if (fallingObject.getClass().toString().contains("BombObject")) {
					log.info("The user collected a bomb.");
					AudioInputStream audioIn;
					try {
						audioIn = AudioSystem.getAudioInputStream(new File("bomb.wav"));
						// Get a sound clip resource.
						Clip clip = AudioSystem.getClip();
						// Open audio clip and load samples from the audio input stream.
						clip.open(audioIn);
						clip.start();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						log.error("Couldn't play the bombing soundeffect !!!");
					}
					moving.remove(index);
					memento = careTaker.get();
					int LeftSize = memento.getLeftStick();
					int RightSize = memento.getRightStick();
					OrgMeme(LeftSize, RightSize);
					score.setLeftHeight(memento.getLeftH());
					score.setRightHeight(memento.getRightH());
					return true;
				}
				
				if (fallingObject.getClass().toString().contains("GiftObject")) {
					log.info("The user collected a gift.");
					score.setScore(score.getScore() + 50);
					moving.remove(index);
					return true;
				}
				
				if (fallingObject.getClass().toString().contains("LiveObject")) {
					log.info("The user collected an extralive.");
					lives++;
					score.setLives(lives);
					moving.remove(index);
					return true;
				}

				left = true;
				fallingObject.setY(leftStick.getY() - score.getLeftHeight() - fallingObject.getHeight());
				moving.remove(index);
				((ColorDecorator) fallingObject)
						.setState(new ControlableObject(leftStick.getX() + (leftStick.getWidth() / 3) - (fallingObject.getWidth() / 2), fallingObject.getY()));
				control.add(fallingObject);
				leftStickObject.add(fallingObject);
				gameData.notifyObservers();
				
				if (leftStick.getY() - score.getLeftHeight() <= 0) {
					score.setLives(0);
				}
				
			}
		}

		if ((rightStick.getY() - score.getRightHeight()) - yFall <= this.difference && yFall <= (rightStick.getY() - score.getRightHeight())) {
			
			if ((rightStick.getX() + (rightStick.getWidth() / 3) >= xFall && rightStick.getX() + (rightStick.getWidth() / 3) <= xFall + fallingObject.getWidth())    ||    (rightStick.getX() + rightStick.getWidth() >= xFall && rightStick.getX() + rightStick.getWidth() <= xFall + fallingObject.getWidth())) {

				log.info("An intersect took place on the right stick.");
				
				if (fallingObject.getClass().toString().contains("BombObject")) {
					log.info("The user collected a bomb.");
					AudioInputStream audioIn;
					try {
						audioIn = AudioSystem.getAudioInputStream(new File("bomb.wav"));
						// Get a sound clip resource.
						Clip clip = AudioSystem.getClip();
						// Open audio clip and load samples from the audio input stream.
						clip.open(audioIn);
						clip.start();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						log.error("Couldn't play the bombing soundeffect !!!");
					}
					moving.remove(index);
					memento = careTaker.get();
					int LeftSize = memento.getLeftStick();
					int RightSize = memento.getRightStick();
					OrgMeme(LeftSize, RightSize);
					score.setLeftHeight(memento.getLeftH());
					score.setRightHeight(memento.getRightH());
					return true;
				}
				
				if (fallingObject.getClass().toString().contains("SkullObject")) {
					log.info("The user collected a skull.");
					lives--;
					score.setLives(lives);
					moving.remove(index);
					return true;

				}
				
				if (fallingObject.getClass().toString().contains("GiftObject")) {
					log.info("The user collected a gift.");
					score.setScore(score.getScore() + 50);
					moving.remove(index);
					return true;
				}
				
				if (fallingObject.getClass().toString().contains("LiveObject")) {
					log.info("The user collected an extralive.");
					lives++;
					score.setLives(lives);
					moving.remove(index);
					return true;
				}
				
				right = true;
				fallingObject.setY(rightStick.getY() - score.getRightHeight() - fallingObject.getHeight());
				moving.remove(index);
				((ColorDecorator) fallingObject)
						.setState(new ControlableObject(rightStick.getX() + (rightStick.getWidth() * 2 / 3) - (fallingObject.getWidth() / 2), fallingObject.getY()));
				control.add(fallingObject);
				rightStickObject.add(fallingObject);
				gameData.notifyObservers();
				
				if (rightStick.getY() - score.getRightHeight() <= 0) {
					score.setLives(0);
				}

			}
		}

		return left || right;

	}
	
	public int getScore() {
		return score.getScore();
	}
	
	public int getLives() {
		return score.getLives();
	}
	
	public void OrgMeme (int LefS, int rigS) {
		
		while (rightStickObject.size() > rigS) {
			control.remove(rightStickObject.remove(rigS));
		}
		while (leftStickObject.size() > LefS) {
			control.remove(leftStickObject.remove(LefS));
		}
		score.setLeftS(leftStickObject.size());
		score.setRightS(rightStickObject.size());
	}
}
