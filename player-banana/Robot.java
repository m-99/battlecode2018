import bc.*;

import java.util.Arrays;
import java.util.List;

public class Robot {

	private int id; 
	private MapLocation location;
	private long health;
	
	public Robot(int unit_id, MapLocation loc, long h) {
		id = unit_id;
		location = loc;
		health = h;
	}
	
    public void randomMove(GameController gc, int id) {
        List<Direction> directions = Arrays.asList(Direction.values());
        while (!directions.isEmpty()) {
            Direction randomDirection = directions.remove((int)(Math.random()*directions.size()));
            if (gc.canMove(id, randomDirection) && gc.isMoveReady(id)) {
                try {
                    gc.moveRobot(id, randomDirection);
                    //return;
                } catch (Exception e) {
                    System.out.println("Robot Exception: randomMove");
                }
            }
        }
    }

    public static void directedMove(GameController gc, int id, Direction direction) {
        try {
            gc.moveRobot(id, direction);
        } catch (Exception e) {
            System.out.println("Robot Exception: directedMove");
        }
    }
    
    public int getUnitId() {
    	return id;
    }
    public MapLocation getMapLocation() {
    	return location;
    }
    public long getHealth() {
    	return health;
    }
    
    public void setUnitId(int unit_id) {
    	id = unit_id;
    }
    public void setMapLocation(MapLocation loc) {
    	location = loc
    }
    public void setHealth(long h) {
    	health = h;
    }
}
