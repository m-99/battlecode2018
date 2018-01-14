// import the API.
// See xxx for the javadocs.
import bc.*;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();
        Direction[] directions = Direction.values();

        //strategy state variables:
        boolean init_worker_pop = true;

        while (true) {
            System.out.println("Current round: "+gc.round());
            VecUnit units = gc.myUnits();




            //unit incrementation loop
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                if(unit.location().isOnPlanet(Planet.Earth)){
                    switch(unit.unitType()){
                        case Worker:
                            //a lot of this code will be elsewhere later

                            Random r = new Random();
                            Direction randDir;
                            ArrayList<Direction> validDir = new ArrayList<Direction>();
                            for(Direction direction : directions){
                                if(gc.canMove(unit.id(), direction)) {
                                    validDir.add(direction);
                                }
                            }
                            if(validDir.size() > 0) {
                                randDir = validDir.get(r.nextInt(validDir.size()));
                                if(gc.canBlueprint(unit.id(), UnitType.Factory, randDir)){
                                    gc.blueprint(unit.id(), UnitType.Factory, randDir);
                                }
                                randDir = validDir.get(r.nextInt(validDir.size()));
                                if(gc.canMove(unit.id(), randDir) && gc.isMoveReady(unit.id())){
                                    gc.moveRobot(unit.id(), randDir);
                                }

                            }


                            break;
                        case Mage:
                            break;
                        case Healer:
                            break;
                        case Knight:
                            break;
                        case Ranger:
                            break;
                        case Factory:
                            break;
                        case Rocket:
                            break;
                    }
                }

            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    public static int getWorkers(VecUnit units){
        int count = 0;
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if(unit.unitType() == UnitType.Worker){
                count++;
            }
        }
        return count;
    }

    public static int getKnights(VecUnit units){
        int count = 0;
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if(unit.unitType() == UnitType.Knight){
                count++;
            }
        }
        return count;
    }
}