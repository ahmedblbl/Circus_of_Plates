package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.List;

import eg.edu.alexu.csd.oop.cs19.world.IIterator;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Iterator implements IIterator {

	private List<GameObject> data;
	private int cursor = -1;

	public Iterator(List<GameObject> data) {
		this.data = data;
	}

	@Override
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	@Override
	public int getCursor() {
		return cursor;
	}

	@Override
	public boolean hasNext() {
		if (data.size() == 0) {
			return false;
		} else if (cursor == data.size() - 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public GameObject next() {
		if (this.hasNext()) {
			cursor++;
			GameObject x = data.get(cursor);
			return x;
		} else {
			return null;
		}
	}

	@Override
	public boolean hasPrevious() {
		if (data.size() == 0) {
			return false;
		} else if (cursor == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public GameObject previous() {
		if (this.hasPrevious()) {
			cursor--;
			GameObject x = data.get(cursor);
			return x;
		} else {
			return null;
		}
	}

}
