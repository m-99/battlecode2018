import bc.*;

public class Worker extends Robot {
    public static void ability(GameController gc, int id, Direction direction) {
    	//replication
     	if(gc.canReplicate(id, direction) && gc.karbonite() > 15) {
       		gc.replicate(id, direction);
       	}
    }
}
