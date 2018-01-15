import bc.*;

public class Mage {
	public static void ability(GameController gc, int id, MapLocation mapL) {
    	//Blink
    	if(gc.canBlink(id, mapL)) {
    		gc.blink(id, mapL);
    	}
    }
}