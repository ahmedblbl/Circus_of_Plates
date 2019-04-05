package eg.edu.alexu.csd.oop.cs19.world;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.cs19.logic.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class ColorDecorator implements GameObject {
	protected GameObject gameObject;

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return gameObject.getX();
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		gameObject.setX(x);
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return gameObject.getY();
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		gameObject.setY(y);
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return gameObject.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return gameObject.getHeight();
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return gameObject.isVisible();
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return gameObject.getSpriteImages();
	}

	public void setState(PlateType state) {
		((ShapeObject) gameObject).setPlateType(state);
	}

	public void setObject(GameObject object) {
		this.gameObject = object;
	}
}
