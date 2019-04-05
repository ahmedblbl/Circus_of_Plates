package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class PlayerObject implements GameObject {
	
	private int x;
	private int y;
	private int width = 20;
	private int height = 20;
	private boolean visible = true;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int type;
	
	public PlayerObject(int posX, int posY, String path){
		this(posX, posY, path, 0);
	}
	
	public PlayerObject(int posX, int posY, BufferedImage img) {
		this.x = posX;
		this.y = posY;
		this.visible = true;
		spriteImages[0] = img;
	}
	
	public PlayerObject(int posX, int posY, String path, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;
		try {
			File f = new File(path);
			spriteImages[0] = ImageIO.read(f);
			this.width = 193;
			this.height = 290;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getWidth() {
		return this.spriteImages[0].getHeight();
	}

	@Override
	public int getHeight() {
		return this.spriteImages[0].getWidth();
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return spriteImages;
	}

}
