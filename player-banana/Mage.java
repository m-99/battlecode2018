import bc.*;

public class Mage implements Machine {

	private GameController gc;
	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Mage(GameController controller, int unit_id, MapLocation loc, long h) {
		gc = controller;
		id = unit_id;
		location = loc;
		health = h;
	}

	public void ability(GameController gc, int id, MapLocation mapL) {
    	//Blink
    	if(gc.canBlink(id, mapL)) {
    		gc.blink(id, mapL);
    	}
    }

	public void doTarget(Target target){
		switch(target.getTask()){
			case NONE:
				break;
			case MOVE:
				//do math to get direction
				//directedMove(gc, target.getMapLocation());
				break;
			case RANDOM_MOVE:
				break;
				//randomMove(gc, etc);
			case ATTACK:
				break;
			case BLINK:
				break;
			default:
				System.out.println("Passed invalid job");
				break;
		}
	}
}