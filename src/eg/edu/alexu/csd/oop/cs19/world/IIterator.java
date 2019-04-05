package eg.edu.alexu.csd.oop.cs19.world;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface IIterator {

	/* Return Cursor Position */
	public int getCursor();

	/* Return Cursor Position */
	public void setCursor(int cursor);

	/* Return boolean check if table has next node */
	public boolean hasNext();

	/* Return The Next Node Object */
	public GameObject next();

	/* Return boolean check if table has previous node */
	public boolean hasPrevious();

	/* Return The Previous Node Object */
	public GameObject previous();

}