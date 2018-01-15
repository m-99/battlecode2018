import bc.*;

public class Mage {

	//variables can be accessed using the super class
	public Mage(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
	}
	
	public void ability(GameController gc, int id, MapLocation mapL) {
    	//Blink
    	if(gc.canBlink(id, mapL)) {
    		gc.blink(id, mapL);
    	}
    }
}