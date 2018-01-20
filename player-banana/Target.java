import bc.*;

public class Target {

    private Tasks tasks;
    private MapLocation location;
    private int target_id;
    private Direction dir;
    private UnitType structure;

    //pass parameters based on specific task
    //if no target, target id = -1
    //if no direction, direction = center?
    //if no maplocation, location = new MapLocation(Planet.Earth, 0, 0)
    //this is up for debate
    //UnitType is only for blueprints...default could be just a factory??

    //I want to make more constructors, but I will do that tomorrow
    public Target(Tasks t, MapLocation l, int t_id, Direction direction, UnitType st) {
        tasks = t;
        location = l;               
        target_id = t_id;
        dir = direction;
        structure = st;
    }

    public Tasks getTask() {
        return tasks;
    }
    public MapLocation getMapLocation() {
        return location;
    }
    public int getTargetID() { return target_id; }
    public Direction getDirection() { return dir; }
    public UnitType getStructure() { return structure; }

    public void setTask(Tasks ta) {    
    	tasks = ta;
    }
    public void setMapLocation(MapLocation lo) {
        location = lo;
    }
    public void setTargetID(int t_id) { target_id = t_id; }
    public void setDirection(Direction d) { dir = d; }
    public void setStructure(UnitType st) { structure = st; }
}
