package eg.edu.alexu.csd.oop.cs19.world;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface DataContaineer {
	
	/* return Iterator Object */
	public IIterator getIterator();
	
	/* Set The Cursor State */
	public void setCursor(int cursor);


}
