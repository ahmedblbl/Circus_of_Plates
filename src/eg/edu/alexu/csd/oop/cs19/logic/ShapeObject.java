package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.cs19.world.PlateType;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ShapeObject implements GameObject {
	private String path;
	private PlateType plateType;
	private int width = 20;
	private int height = 20;
	private boolean visible = true;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int type;
	

	public ShapeObject(int posX, int posY, BufferedImage img) {
		this.visible = true;
		spriteImages[0] = img; 
		this.width = 193;
		this.height = 290;
	}

	@Override
	public int getX() {
		return plateType.getPosition().getX();
	}

	@Override
	public void setX(int x) {
		plateType.setX(x);
	}

	@Override
	public int getY() {
		return plateType.getPosition().getY();
	}

	@Override
	public void setY(int y) {
		plateType.setY(y);
	}

	@Override
	public int getWidth() {
		return this.spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return this.spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	public void setPlateType(PlateType plateType) {
		this.plateType = plateType;
	}
	
	public void setPath(String path) {
		this.path = (path);
	}
	public String getPath() {
		return path;
	}
}
