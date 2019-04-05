package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BlackObject extends ColorDecorator {
	
	public BlackObject(GameObject gameObject) {
		super.gameObject = gameObject;
	}
	
	public BlackObject() {
		
	}
	
}
