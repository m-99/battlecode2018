import bc.*;

public class Ranger {
	public static void ability(GameController gc, int id, MapLocation mapL) {
    	//Begin Snipe
    	if(gc.canBeginSnipe(id, mapL)) {
    		gc.beginSnipe(id, mapL);
    	}
    }
}