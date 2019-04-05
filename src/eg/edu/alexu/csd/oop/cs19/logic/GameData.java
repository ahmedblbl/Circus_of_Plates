package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import eg.edu.alexu.csd.oop.cs19.world.Observer;
import eg.edu.alexu.csd.oop.cs19.world.Subject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class GameData implements Subject {

	private static List<GameObject> leftStick1;
	private static List<GameObject> rightStick1;
    private static ArrayList<Observer> observerList;
    private List <GameObject> control;
    
	public GameData(List<GameObject> leftStick, List<GameObject> rightStick, List<GameObject> control) {
		
		leftStick1 = leftStick;
		rightStick1 = rightStick;
		observerList = new ArrayList<>();
		this.control = control;
		
	}
	
    @Override
    public void registerObserver(Observer o) { 
        observerList.add(o); 
    } 
  
    @Override
    public void unregisterObserver(Observer o) { 
        observerList.remove(observerList.indexOf(o)); 
    } 
  
    @Override
    public void notifyObservers() 
    { 
        for (Iterator<Observer> it = 
              observerList.iterator(); it.hasNext();) 
        { 
            Observer o = it.next(); 
            o.update(leftStick1, rightStick1,control); 
        }
    	
    } 
	
}
