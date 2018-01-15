import bc.*;

public class Knight {
	
	//variables can be accessed using the super class
	public Knight(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
	}
	
	public void ability(GameController gc, int id, int target_id) {
    	//javelin
    	if(gc.canJavelin(id, target_id)) {
    		gc.javelin(id, target_id);
    	}
    }
}