import bc.*;

public class Target {

    private Tasks tasks;
    private MapLocation location;
    private int targetID;
    private Direction dir;
    private UnitType structure;

    //pass parameters based on specific task
    //if no target, target id = -1
    //if no direction, direction = center?
    //if no maplocation, location = new MapLocation(Planet.Earth, 0, 0)
    //this is up for debate
    //UnitType is only for blueprints...default could be just a factory??

    //I want to make more constructors, but I will do that tomorrow
    //Most generic constructor
    public Target(Tasks t, MapLocation l, int tID, Direction direction, UnitType st) {
        tasks = t;
        location = l;               
        targetID = tID;
        dir = direction;
        structure = st;
    }

    //default constructor
    public Target() {
        tasks = Tasks.NONE;
        location = new MapLocation(Planet.Earth, 0,0);
        targetID = -1;
        dir = Direction.Center;
        structure = UnitType.Factory;
    }

    //for MOVE, SNIPE, and BLINK
    public Target(Tasks t, MapLocation l) {
        tasks = t;
        location = l;
        targetID = -1;
        dir = Direction.Center;
        structure = UnitType.Factory;
    }

    //for REPAIR, JAVELIN, BUILD, ATTACK, and HEAL
    public Target(Tasks t, int tID) {
        tasks = t;
        location = new MapLocation(Planet.Earth, 0,0);
        targetID = tID;
        dir = Direction.Center;
        structure = UnitType.Factory;
    }

    //for BLUEPRINT
    public Target(Tasks t, UnitType str, Direction d) {
        tasks = t;
        location = new MapLocation(Planet.Earth, 0,0);
        targetID = -1;
        dir = d;
        structure = str;
    }

    //for REPLICATE, HARVEST
    public Target(Tasks t, Direction d) {
        tasks = t;
        location = new MapLocation(Planet.Earth, 0,0);
        targetID = -1;
        dir = d;
        structure = UnitType.Factory;
    }


    public Tasks getTask() {
        return tasks;
    }
    public MapLocation getMapLocation() {
        return location;
    }
    public int getTargetID() { return targetID; }
    public Direction getDirection() { return dir; }
    public UnitType getStructure() { return structure; }

    public void setTask(Tasks ta) {    
    	tasks = ta;
    }
    public void setMapLocation(MapLocation lo) {
        location = lo;
    }
    public void setTargetID(int t_id) { targetID = t_id; }
    public void setDirection(Direction d) { dir = d; }
    public void setStructure(UnitType st) { structure = st; }

    public String toString() {
        System.out.println("Task: " + tasks);
    }
}
