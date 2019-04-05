package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class ImageObject implements GameObject{
	abstract public String getColor();
	abstract public String getPath();
}
