import bc.*;

public class Ranger extends Robot{

	private GameController gc;
	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Ranger(GameController controller, int unit_id, MapLocation loc, long h){
		super(unit_id, loc, h);
		gc = controller;
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

	public void doTask(Target target){
		switch(target.getTask()){
			case NONE:
				break;
			case MOVE:
				//do math to get direction
				//directedMove(gc, target.getMapLocation());
				break;
			case RANDOM_MOVE:
				//randomMove(gc, etc);
			case ATTACK:
				break;
			case SNIPE:
				break;
			default:
				System.out.println("Passed invalid job");
				break;

		}
	}
}