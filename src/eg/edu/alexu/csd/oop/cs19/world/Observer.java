package eg.edu.alexu.csd.oop.cs19.world;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Observer {
    public void update(List<GameObject> leftStick, List<GameObject> rightStick,List<GameObject>control); 
}
