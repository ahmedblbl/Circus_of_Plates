package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.cs19.world.IFlyweight;
import eg.edu.alexu.csd.oop.game.GameObject;

public class FlyWeight implements IFlyweight {

	private static FlyWeight fly;
	private HashMap<String, ArrayList<GameObject>> gameObjects = new HashMap<>();
	private ColorFactory colorFactory;
	private JarReader rReader;
	private List<Class<? extends ColorDecorator>> shapeClasses;
	private HashMap<String, BufferedImage> paths;
	private List<String> colors;
	private HashMap<String, BufferedImage> extras;
	private HashMap<BufferedImage, String> resPath;
	private static Logger log;

	private FlyWeight() {

		log = Logger.getLogger(FlyWeight.class.getName());
		PropertyConfigurator.configure("log4j.properties");

		rReader = new JarReader();
		
		String[] temp = getClass().getCanonicalName().split(getClass().getPackage().getName());
		File file = new File(temp[0]);
		System.out.println("Package name: " + file.getAbsolutePath());

		try {
			log.info("Loading the shapes classes from the jar file ...");
			shapeClasses = rReader.getClassesFromJar(file.getAbsolutePath() + "\\LibColors.jar");
		} catch (Exception e) {
			log.fatal("Couldn't load the shapes classes from the jar file !!!");
		}
		try {
			log.info("Loading the shapes images from the jar file ...");
			paths = rReader.getPlatesFromJar(file.getAbsolutePath() + "\\LibImages.jar");
		} catch (Exception e) {
			log.fatal("Couldn't load the shapes images from the jar file !!!");
		}

		colors = new ArrayList<>();
		extras = new HashMap<>();
		this.shapeClassesToColors();

		resPath = this.pathsToPath();

		colorFactory = new ColorFactory(shapeClasses);

	}

	private void shapeClassesToColors() {

		for (int i = 0; i < shapeClasses.size(); i++) {
			String color = shapeClasses.get(i).getSimpleName().split("Object")[0].toLowerCase();
			colors.add(color);
		}
	}

	private HashMap<BufferedImage, String> pathsToPath() {

		// List<List<String>> op = new ArrayList<>();

		HashMap<BufferedImage, String> res = new HashMap<>();

		for (String key : paths.keySet()) {

			String p = key.toLowerCase();

			for (int j = 0; j < colors.size(); j++) {
				String color = colors.get(j);
				if (p.contains(color)) {

					if (color.equalsIgnoreCase("bomb") || color.equalsIgnoreCase("gift")
							|| color.equalsIgnoreCase("skull") || color.equalsIgnoreCase("live")) {
						extras.put(color, paths.get(key));
						continue;
					}

					res.put(paths.get(key), color);

				}
			}

		}

		/*
		 * String[][] path = new String[op.size()][2];
		 * 
		 * for (int i = 0; i < op.size(); i++) { for (int j = 0; j < op.get(i).size();
		 * j++) { path[i][j] = op.get(i).get(j); } }
		 */

		return res;
	}

	public static IFlyweight getInstance() {
		if (fly == null)
			fly = new FlyWeight();
		return fly;
	}

	@Override
	public GameObject getObject() {

		log.info("Creating a new movable Shape object ...");

		int size = resPath.size();
		Random r = new Random();
		int random = r.nextInt(size);
		String choosen = "";

		int i = 0;

		BufferedImage img = null;

		for (BufferedImage key : resPath.keySet()) {

			if (i == random) {

				choosen = resPath.get(key);
				img = key;

			}

			i++;

		}

		// ((ShapeObject)returned).setPath(choosen);
		GameObject returned = colorFactory.getObject(choosen, new ShapeObject(0, 0, img));
		return returned;

	}

	@Override
	public GameObject getObject(String path) {

		log.info("Creating a new Special Shape ...");

		GameObject returned = new ShapeObject(0, 0, extras.get(path));

		if (path.contains("bomb")) {
			returned = colorFactory.getObject("bomb", returned);
			// returned = new BombObject(returned);
		} else if (path.contains("gift")) {
			returned = colorFactory.getObject("gift", returned);
			// returned = new GiftObject(returned);
		} else if (path.contains("skull")) {
			returned = colorFactory.getObject("skull", returned);
			// returned = new SkullObject(returned);
		} else if (path.contains("live")) {
			returned = colorFactory.getObject("live", returned);
			// returned = new SkullObject(returned);
		}

		return returned;
	}

	@Override
	public void resetObject(GameObject g) {
		// TODO Auto-generated method stub
		String path = ((ShapeObject) g).getPath();
		if (gameObjects.containsKey(path)) {
			gameObjects.get(path).add(g);
		} else {
			gameObjects.put(path, new ArrayList<>());
			gameObjects.get(path).add(g);
		}
	}

}
