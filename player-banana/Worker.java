import bc.*;

public class Worker extends Robot {

	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Worker(int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
		id = unit_id;
		location = loc;
		health = h;
	}

	public void ability(GameController gc, Direction direction) {
    	//replication
     	if(gc.canReplicate(id, direction) && gc.karbonite() > 15) {
       		gc.replicate(id, direction);
       	}
    }
}
