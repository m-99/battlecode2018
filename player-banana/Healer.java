import bc.*;

public class Healer extends Robot {
	
	//variables can be accessed using the super class
	public Healer(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
	}
	
	public void ability(GameController gc, int id, int target_id) {
    	//Heal
    	if(gc.canHeal(id, target_id)) {
    		gc.heal(id, target_id);
    	}
    }
}