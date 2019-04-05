package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

	private List<Memento> mementoList = new ArrayList<>();
	public CareTaker () {
		mementoList.add(new Memento(0, 0, 0, 0));
	}

	public void add(Memento iMemento) {
		mementoList.clear();
		mementoList.add(iMemento);
	}

	public Memento get() {
		Memento x = mementoList.get(mementoList.size() - 1);
		if(mementoList.size() == 0) {
			mementoList.add(new Memento(0, 0, 0, 0));
		}
		return x;	
	}

}
