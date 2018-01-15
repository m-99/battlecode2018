import bc.*;

public class Target {

        private Tasks tasks;
        private MapLocation location;

        public Target(Tasks t, MapLocation l) {
                tasks = t;
                location = l;                                                            
        }

        public Tasks getTask() {
                return tasks;
        }
        public MapLocation getMapLocation() {
                return location;
        }
        public void setTask(Tasks ta) {
                tasks = ta;
        }
        public void setMapLocation(MapLocation lo) {
                location = lo;
        }

}
