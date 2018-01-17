import bc.*;

//possible parent class instead of separating robots and structures?
public class Machine {
    private int id;
    private long health;
    private MapLocation location;

    public Machine(int unit_id, MapLocation loc) {
        id = unit_id;
        location = loc;
        health = 100;
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
        location = loc;
    }
    public void setHealth(long h) {
        health = h;
    }
}
