import bc.*;

public class Knight extends Robot{

	private GameController gc;
	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Knight(GameController controller, int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
		gc = controller;
		id = unit_id;
		location = loc;
		health = h;
	}
	
	public void ability(GameController gc, int id, int target_id) {
    	//javelin
    	if(gc.canJavelin(id, target_id)) {
    		gc.javelin(id, target_id);
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
				break;
			case ATTACK:
				break;
			case JAVELIN:
				break;
			default:
				System.out.println("Passed invalid job");
				break;

		}
	}
}