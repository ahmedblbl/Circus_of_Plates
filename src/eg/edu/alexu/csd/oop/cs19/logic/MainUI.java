package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import eg.edu.alexu.csd.oop.cs19.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.*;

public class MainUI {

	private static Logger log;

	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("(dd-MM-yyyy) (hh-mm-ss)");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		log = Logger.getLogger(MainUI.class.getName());
		PropertyConfigurator.configure("log4j.properties");
		log.info("Starting the Game ...");

		JMenuBar menuBar = new JMenuBar();
		JButton newGameBtn = new JButton("new Game");
		newGameBtn.setEnabled(true);
		JButton pauseBtn = new JButton("Pause");
		pauseBtn.setEnabled(true);
		JButton resumeBtn = new JButton("resume");
		resumeBtn.setEnabled(false);
		JButton top = new JButton("Top Score");
		top.setEnabled(true);
		JMenu menu = new JMenu("File");
		JMenuItem EasyLvlItem = new JMenuItem("Easy");
		JMenuItem MedLvlItem = new JMenuItem("Medium");
		JMenuItem HardLvlItem = new JMenuItem("Hard");
		menu.add(EasyLvlItem);
		menu.addSeparator();
		menu.add(MedLvlItem);
		menu.addSeparator();
		menu.add(HardLvlItem);
		JLabel scoreLbl = new JLabel("Score:   " + 0);
		JLabel livesLbl = new JLabel("Lives:   " + 3);
		JLabel lvlLabel = new JLabel("Level:   ");
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		//////////////////////////////////
		menuBar.add(menu);
		menuBar.add(new JSeparator());
		menuBar.add(newGameBtn);
		menuBar.add(new JSeparator());
		menuBar.add(pauseBtn);
		menuBar.add(new JSeparator());
		menuBar.add(resumeBtn);
		menuBar.add(new JSeparator());
		menuBar.add(top);
		menuBar.add(new JSeparator());
		menuBar.add(scoreLbl);
		menuBar.add(new JSeparator());
		menuBar.add(livesLbl);
		menuBar.add(new JSeparator());
		menuBar.add(lvlLabel);
		/////////////////////////////////
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());

		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("circus_music.wav"));
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Couldn't load Background music !!!");
		}
		// GameController g = GameEngine.start("Circus Of Plates", new Circus(1890,
		// 1000, ObjectFactory.getInstance()), menuBar,Color.white);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Context context = new Context();
		GameController g = GameEngine.start("Circus Of Plates",
				new Circus(Math.min(width, 1800), Math.min(height, 1000), ObjectFactory.getInstance(), new Easy(context), context, scoreLbl, livesLbl, lvlLabel), menuBar,
				Color.white);

		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.info("Game Paused.");
				resumeBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				g.pause();
			}
		});

		resumeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.info("Game Resumed.");
				pauseBtn.setEnabled(true);
				resumeBtn.setEnabled(false);
				g.resume();
			}
		});

		top.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				log.info("Showing the leaderboard ...");
				g.pause();
				try {
					ArrayList<Integer> top = new ArrayList<>();
					LeaderBoard board = new Board(top);
					board.ReadScores();
					JPanel jPanel = new JPanel(new GridLayout(0, 1));
					for (int i = 0; i < top.size(); i++) {
						Label label = new Label(i + 1 + "-  " + top.get(i));
						label.setFont(new Font("Old Standard TT", Font.BOLD, 22));
						jPanel.add(label);
					}
					JOptionPane.showMessageDialog(null, jPanel, "Top Scores", JOptionPane.OK_OPTION);
				} catch (Exception e) {
					log.error("Couldn't load the leaderboard !!!");
				}
				g.resume();
			}
		});

		newGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.info("starting a new game ...");
				Context context = new Context();
				g.changeWorld(
						new Circus(1890, 1000, ObjectFactory.getInstance(), new Easy(context), context, scoreLbl, livesLbl, lvlLabel));
			}
		});

		EasyLvlItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("starting a new easy game ...");
				Context context = new Context();
				g.changeWorld(
						new Circus(1890, 1000, ObjectFactory.getInstance(), new Easy(context), context, scoreLbl, livesLbl, lvlLabel));
			}
		});

		MedLvlItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("starting a new medium game ...");
				Context context = new Context();
				g.changeWorld(new Circus(1890, 1000, ObjectFactory.getInstance(), new Medium(context), context, scoreLbl, livesLbl,
						lvlLabel));
			}
		});

		HardLvlItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("starting a new hard game ...");
				Context context = new Context();
				g.changeWorld(
						new Circus(1890, 1000, ObjectFactory.getInstance(), new Hard(context), context, scoreLbl, livesLbl, lvlLabel));
			}
		});

	}

}
