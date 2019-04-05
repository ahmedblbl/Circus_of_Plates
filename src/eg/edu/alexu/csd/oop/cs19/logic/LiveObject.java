package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class LiveObject extends ColorDecorator {

	public LiveObject(GameObject gameObject) {
		super.gameObject = gameObject;
	}

	public LiveObject() {

	}

}