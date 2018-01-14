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
                Random r = new Random();
                Direction randDir = directions[r.nextInt(8)];

                //worker logic
                boolean workerStart = true;
                if(unit.unitType() == UnitType.Worker) {
                    for(int x = 0; x<8; x++) {
                        System.out.println(units.size());
                        if(workerStart == true){
                            if(gc.canReplicate(unit.id(), randDir) && gc.karbonite() >= 15) {
                                gc.replicate(unit.id(), randDir);
                                break;
                            }
                            if(units.size() >= 10) {
                                workerStart = false;
                            }
                        }
                        else {
                            if(gc.canBlueprint(unit.id(), UnitType.Factory, randDir)) {
                                gc.blueprint(unit.id(), UnitType.Factory, randDir);
                                System.out.println("BUILDING");
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