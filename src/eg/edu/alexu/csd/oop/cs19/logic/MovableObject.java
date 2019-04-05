package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.PlateType;

public class MovableObject implements PlateType {

	private Position position;
	
	public MovableObject(int posX, int posY) {
		position = new Position(posX, posY);
	}

	
	@Override
	public int setX(int x) {
		return position.getX();
	}
	
	@Override
	public int setY(int y) {
		position.setY(y);
		return position.getY();
	}


	@Override
	public Position getPosition() {
		return this.position;
	}

}
