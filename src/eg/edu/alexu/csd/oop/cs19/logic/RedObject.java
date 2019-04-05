package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class RedObject extends ColorDecorator {

	public RedObject(GameObject gameObject) {
		super.gameObject = gameObject;
	}

	public RedObject() {

	}

}
