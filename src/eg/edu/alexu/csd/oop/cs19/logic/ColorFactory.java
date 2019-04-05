package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.List;

import eg.edu.alexu.csd.oop.cs19.world.ColorDecorator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ColorFactory {
	
	private List<Class<?extends ColorDecorator>> shapes;

	public ColorFactory(List<Class<?extends ColorDecorator>> shapes) {
		this.shapes = shapes;
	}

	public GameObject getObject(String color, GameObject Object) {
		
		for (int i = 0; i < this.shapes.size(); i++) {
			
			Class<?extends ColorDecorator> c = this.shapes.get(i);
			if (c.getSimpleName().toLowerCase().contains(color)) {
				
				try {
					GameObject cls = c.newInstance();
					((ColorDecorator) cls).setObject(Object);
					return cls;
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		return null;
	}
}
