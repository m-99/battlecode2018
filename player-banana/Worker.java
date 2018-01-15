import bc.*;

public class Worker extends Robot {
    
	//variables can be accessed using the super class
	public Worker(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
	}
	
	public void ability(GameController gc, int id, Direction direction) {
    	//replication
     	if(gc.canReplicate(id, direction) && gc.karbonite() > 15) {
       		gc.replicate(id, direction);
       	}
    }
}
