package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ObjectFactory implements Factory {
	
	private String p = "src\\eg\\edu\\alexu\\csd\\oop\\cs19\\images\\plates\\";
	private static ObjectFactory factory;
	private JarReader rReader;

	private ObjectFactory() {
		rReader = new JarReader();
	}

	public static ObjectFactory getInstance() {
		if (factory == null)
			factory = new ObjectFactory();
		return factory;
	}

	@Override
	public GameObject createObject(String name, int posX, int posY, BufferedImage img) {
		
		if (name.toLowerCase().equals("player")) {

			return new PlayerObject(posX, posY, img);

		} else if (name.toLowerCase().equals("shape")) {

			ColorDecorator s = (ColorDecorator) FlyWeight.getInstance().getObject();
			s.setState(new MovableObject(posX, posY));
			
			return s;

		} else if (name.toLowerCase().equals("stick")) {
			
			return new StickObject(posX, posY, img);
			
		}else if(name.toLowerCase().equals("bomb")){
			
			ColorDecorator s = (ColorDecorator) FlyWeight.getInstance().getObject("bomb");
			s.setState(new MovableObject(posX, posY));
			return s;
			
		} else if(name.toLowerCase().equals("gift")){
			
			ColorDecorator s = (ColorDecorator) FlyWeight.getInstance().getObject("gift");
			s.setState(new MovableObject(posX, posY));
			return s;

		} else if(name.toLowerCase().equals("skull")){
			
			ColorDecorator s = (ColorDecorator) FlyWeight.getInstance().getObject("skull");
			s.setState(new MovableObject(posX, posY));
			return s;
		} else if(name.toLowerCase().equals("live")){
			
			ColorDecorator s = (ColorDecorator) FlyWeight.getInstance().getObject("live");
			s.setState(new MovableObject(posX, posY));
			return s;
		}
		
		return null;
	}

}
