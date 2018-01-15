import bc.*;

public class Knight {
	public static void ability(GameController gc, int id, int target_id) {
    	//javelin
    	if(gc.canJavelin(id, target_id)) {
    		gc.javelin(id, target_id);
    	}
    }
}