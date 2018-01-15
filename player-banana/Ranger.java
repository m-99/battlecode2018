import bc.*;

public class Ranger {
	
	//variables can be accessed using the super class
	public Ranger(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
	}
	public void ability(GameController gc, int id, MapLocation mapL) {
    	//Begin Snipe
    	if(gc.canBeginSnipe(id, mapL)) {
    		gc.beginSnipe(id, mapL);
    	}
    }
}