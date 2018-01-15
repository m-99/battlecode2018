import bc.*;

public class Healer {
	public static void ability(GameController gc, int id, int target_id) {
    	//Heal
    	if(gc.canHeal(id, target_id)) {
    		gc.heal(id, target_id);
    	}
    }
}