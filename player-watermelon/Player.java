// import the API.
// See xxx for the javadocs.
import bc.*;
import java.util.Random;

public class Player {
    public static void main(String[] args) {
        // You can use other files in this directory, and in subdirectories.
        Extra extra = new Extra(27);
        System.out.println(extra.toString());

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

                // Most methods on gc take unit IDs, instead of the unit objects themselves.

                Direction randDir = randomDirection();

                if (gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), randDir)) {
                    gc.moveRobot(unit.id(), randDir);
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    static Direction randomDirection() {
        Random r = new Random();
        switch(r.nextInt(8)){
            case 0:
                return Direction.North;
            case 1:
                return Direction.Northeast;
            case 2:
                return Direction.East;
            case 3:
                return Direction.Southeast;
            case 4:
                return Direction.South;
            case 5:
                return Direction.Southwest;
            case 6:
                return Direction.West;
            case 7:
                return Direction.Northwest;
        }
        return Direction.North;
    }
}