package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.cs19.world.IMemento;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Memento implements IMemento{

	private int leftSize = 0;
	private int rightSize = 0;
	private int rightH = 0;
	private int leftH = 0;
	public Memento(int left, int right, int rightH, int leftH) {
		// TODO Auto-generated constructor stub
	
		leftSize = left;
		rightSize = right;
		this.rightH = rightH;
		this.leftH = leftH;
	}
	@Override
	public int getLeftStick() {
		// TODO Auto-generated method stub
		return leftSize;
	}
	@Override
	public int getRightStick() {
		// TODO Auto-generated method stub
		return rightSize;
	}
	@Override
	public int getRightH() {
		// TODO Auto-generated method stub
		return this.rightH;
	}
	@Override
	public int getLeftH() {
		// TODO Auto-generated method stub
		
		return this.leftH;
		
	}
	
	

}
