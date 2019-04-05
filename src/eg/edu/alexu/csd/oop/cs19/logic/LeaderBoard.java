package eg.edu.alexu.csd.oop.cs19.logic;

public abstract class LeaderBoard {
	

	public abstract void setFileName();
	public abstract void addScore();
	public abstract void getAllscores();
	public abstract void getTopScores();
	//the template method
	public final void generateLeader() {
		setFileName();
		addScore();
	}
	
	//the template method
	public final void ReadScores() {
		getAllscores();
		getTopScores();
	} 

}
