package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class SkullObject extends ColorDecorator {

	public SkullObject(GameObject gameObject) {
		super.gameObject = gameObject;
	}

	public SkullObject() {

	}
}
