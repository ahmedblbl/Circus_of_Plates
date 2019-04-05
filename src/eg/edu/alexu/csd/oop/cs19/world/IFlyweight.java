package eg.edu.alexu.csd.oop.cs19.world;
import java.util.Random;

import eg.edu.alexu.csd.oop.cs19.logic.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface IFlyweight {
	
	public GameObject getObject();
	void resetObject(GameObject g);
	public GameObject getObject(String path);
}
