// import the API.
// See xxx for the javadocs.
import bc.*;
import java.util.Random;

public class Player {
    public static void main(String[] args) {

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
                Random r = new Random();
                Direction randDir = directions[r.nextInt(8)];


                /*if (gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), randDir)) {
                    gc.moveRobot(unit.id(), randDir);
                }*/

                VecUnit baddies = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(), 50, Team.Red);
                if(baddies.size() > 0 && gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), Direction.East)){
                    gc.moveRobot(unit.id(), Direction.East);
                }else if(gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), Direction.West)){
                    gc.moveRobot(unit.id(), Direction.West);
                }

                if(gc.canReplicate(unit.id(), randDir)){
                    gc.replicate(unit.id(), randDir);
                }

            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }
}