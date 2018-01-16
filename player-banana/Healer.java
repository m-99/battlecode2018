import bc.*;

public class Healer extends Robot {

	private GameController gc;
	private int id;
	private MapLocation location;
	private long health;

	//variables can be accessed using the super class
	public Healer(GameController controller, int unit_id, MapLocation loc, long h) {
		super(unit_id, loc, h);
		gc = controller;
		id = unit_id;
		location = loc;
		health = h;
	}
	
	public void ability(GameController gc, int id, int target_id) {
    	//Heal
    	if(gc.canHeal(id, target_id)) {
    		gc.heal(id, target_id);
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
			case HEAL:
				break;
			case OVERCHARGE:
				break;
			default:
				System.out.println("Passed invalid job");
				break;

		}
	}
}