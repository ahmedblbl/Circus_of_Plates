package eg.edu.alexu.csd.oop.cs19.world;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Factory {

	public GameObject createObject(String name, int posX, int posY, BufferedImage img);
	
}
