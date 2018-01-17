import bc.*;

public class Worker implements Machine {

	private GameController gc;
	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Worker(GameController controller, int unit_id, MapLocation loc, long h) {
		gc = controller;
		id = unit_id;
		location = loc;
		health = h;
	}

	public void doTarget(Target t) {}

	public void ability( Direction direction) {
    	//replication
     	if(gc.canReplicate(id, direction) && gc.karbonite() > 15) {
       		gc.replicate(id, direction);
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
			case BUILD:
				break;
			case REPAIR:
				break;
			case BLUEPRINT:
				break;
			case REPLICATE:
				break;
			default:
				System.out.println("Passed invalid job");
				break;

		}
	}
}
