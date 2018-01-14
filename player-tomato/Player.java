						// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
    public static void main(String[] args) {

        // MapLocation is a data structure you'll use a lot.
        MapLocation loc = new MapLocation(Planet.Earth, 10, 20);
        System.out.println("loc: "+loc+", one step to the Northwest: "+loc.add(Direction.Northwest));
        System.out.println("loc x: "+loc.getX());

        // One slightly weird thing: some methods are currently static methods on a static class called bc.
        // This will eventually be fixed :/
        System.out.println("Opposite of " + Direction.North + ": " + bc.bcDirectionOpposite(Direction.North));

        // Connect to the manager, starting the game
        GameController gc = new GameController();

        // Direction is a normal java enum.
        Direction[] directions = Direction.values();

        while (true) {
            System.out.println("Current round: "+gc.round());
            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
            VecUnit units = gc.myUnits();
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                
                //worker logic
                if(unit.unitType().equals(UnitType.Worker)) {
                	for(Direction direction : directions) {
                		if(units.size() <= 10) {
                			if(gc.canReplicate(unit.id(), direction) && gc.karbonite() >= 15) {
                				gc.replicate(unit.id(), direction);
                				break;
                			}
                		}
                		else {
                			if(gc.canBlueprint(unit.id(), UnitType.Factory, direction)) {
                				gc.blueprint(unit.id(), UnitType.Factory, direction);
                				break;
                			}
                		}
                	}
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }
}