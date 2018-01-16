import bc.*;

public class Ranger extends Robot{

	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Ranger(int unit_id, MapLocation loc, long h){
		super(unit_id, loc, h);
		id = unit_id;
		location = loc;
		health = h;
	}
	public void ability(GameController gc, int id, MapLocation mapL) {
    	//Begin Snipe
    	if(gc.canBeginSnipe(id, mapL)) {
    		gc.beginSnipe(id, mapL);
    	}
    }
}