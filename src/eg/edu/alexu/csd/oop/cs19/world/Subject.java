package eg.edu.alexu.csd.oop.cs19.world;

public interface Subject {
    public void registerObserver(Observer o); 
    public void unregisterObserver(Observer o); 
    public void notifyObservers(); 
}
