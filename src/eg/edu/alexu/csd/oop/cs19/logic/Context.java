package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;

public class Context {

	private IStateLevel state;

	public void setState(IStateLevel state) {
		this.state = state;
	}
	
	public IStateLevel getState() {
		return this.state;
	}

}
