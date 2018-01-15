import bc.*;

public class Target {

    private Tasks tasks;
    private MapLocation location;
    private UnitType type;

    public Target(Tasks t, MapLocation l, UnitType ty) {
        tasks = t;
        location = l;               
        type = ty;
    }

    public Tasks getTask() {
        return tasks;
    }
    public MapLocation getMapLocation() {
        return location;
    }
    public UnitType getUnitType() {
    	return type;
    }
    public void setTask(Tasks ta) {    
    	tasks = ta;
    }
    public void setMapLocation(MapLocation lo) {
        location = lo;
    }
    public void setUnitType(UnitType typ) {
    	type = typ;
    }

}
