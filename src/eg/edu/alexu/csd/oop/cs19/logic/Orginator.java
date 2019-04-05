package eg.edu.alexu.csd.oop.cs19.logic;



import eg.edu.alexu.csd.oop.cs19.world.IOrginator;

public class Orginator implements IOrginator {
	
	private int leftSize = 0;
	private int rightSize = 0;
	private static int rightH = 0;
	private static int leftH = 0;

	@Override
	public void setState(int left, int right) {
		// TODO Auto-generated method stub
		leftSize = left;
		rightSize = right;
	}

	@Override
	public Memento getMemento() {
		// TODO Auto-generated method stub
		return new Memento(leftSize, rightSize,rightH,leftH);
	}

	public void setHeight(int rightHeight, int leftHeight) {
		// TODO Auto-generated method stub
		rightH = rightHeight;
		leftH = leftHeight;
	}

	public int getRh() {
		return rightH;
	}
	
	public int getLH() {
		return leftH;
	}

}
