package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;

public class JarReader {

	/**
	 * it's used to open and search inside a jar file for any class that extends
	 * Shape interface.
	 * 
	 * @param jarName
	 *            ... the jar file path.
	 * @return ... it returns a list with the classes that extends the Shape
	 *         interface.
	 * 
	 * @throws Exception
	 */
	public List<Class<? extends ColorDecorator>> getClassesFromJar(String jarName) throws Exception {

		List<Class<? extends ColorDecorator>> l = new ArrayList<>();

		URL url = new URL("jar:file:" + jarName + "!/");
		URL[] classURL = new URL[] { url };
		ClassLoader classLoad = new URLClassLoader(classURL);

		JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
		JarEntry jarEntry;

		while (true) {

			jarEntry = jarFile.getNextJarEntry();
			if (jarEntry == null) {
				break;
			}
			if ((jarEntry.getName().endsWith(".class"))) {

				String t = jarEntry.getName().replaceAll("/", "\\.");
				String[] temp = t.split("\\.");

				System.out.println(t.substring(0, t.length() - 6));

				Class c = classLoad.loadClass(t.substring(0, t.length() - 6));

				System.out.println(c.toString());

				if (ColorDecorator.class.isAssignableFrom(c)) {

					l.add(c);

				}

			}
		}

		return l;
	}

	public HashMap<String, BufferedImage> getPlatesFromJar(String jarName) throws Exception {

		HashMap<String, BufferedImage> l = new HashMap<>();

		URL url = new URL("jar:file:" + jarName + "!/");
		URL[] classURL = new URL[] { url };
		ClassLoader classLoad = new URLClassLoader(classURL);

		JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
		JarEntry jarEntry;

		while (true) {

			jarEntry = jarFile.getNextJarEntry();
			if (jarEntry == null) {
				break;
			}
			if ((jarEntry.getName().endsWith(".png"))) {

				String t = jarEntry.getName().replaceAll("/", "\\.");
				String[] temp = t.split("\\.");
				t = temp[temp.length - 2];
				String s = "/" + temp[temp.length - 2] + "." + temp[temp.length - 1];

				if (t.contains("background") || t.contains("clownChar") || t.contains("left") || t.contains("right")) {
					continue;
				}

				BufferedImage img = ImageIO.read(JarReader.class.getResource(s));

				// System.out.println(t.substring(0, t.length() - 6));

				System.out.println("plate: " + s);

				l.put(t, img);

			}
		}

		return l;
	}

	public HashMap<String, BufferedImage> getResFromJar(String jarName) {

		HashMap<String, BufferedImage> res = new HashMap<>();

		try {

			BufferedImage img = ImageIO.read(JarReader.class.getResource("/background.jpg"));
			BufferedImage img2 = ImageIO.read(JarReader.class.getResource("/clownChar.png"));
			BufferedImage img3 = ImageIO.read(JarReader.class.getResource("/left.png"));
			BufferedImage img4 = ImageIO.read(JarReader.class.getResource("/right.png"));
			res.put("background", img);
			res.put("clownChar", img2);
			res.put("left", img3);
			res.put("right", img4);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;

	}

}
