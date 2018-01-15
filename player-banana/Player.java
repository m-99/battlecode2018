// import the API.
// See xxx for the javadocs.
import bc.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();
        Direction[] directions = Direction.values();

        //strategy state variables:
        boolean init_worker_pop = true;

        ArrayList<Target> queue = new ArrayList<Target>();
        HashMap<Integer, Target> jobMap = new HashMap<Integer, Target>();

        while (true) {
            System.out.println("Current round: "+gc.round());

            VecUnit units = gc.myUnits();
            //note: don't need to mark dead units; simply ignoring them in job hashmap for now

            //phase logic
            boolean phase1Queued = false;
            boolean phase2Queued = false;
            boolean phase3Queued = false;
            int phase = 1;

            if(phase == 1 && !phase1Queued ){
                for(int x = 0; x < 10; x++){
                    //add unit type to target
                    queue.add(new Target(Tasks.MOVE, new MapLocation(Planet.Earth, 0, 0)));
                }
            }else if(phase == 2 && !phase2Queued){

            }else if(phase == 3 && !phase3Queued ){

            }

            //add queue items to open spaces in Hashmap
            if(queue.size() > 0){
                for (int i = 0; i < units.size(); i++) {
                    Unit unit = units.get(i);
                    //add unit type to target
                    if (jobMap.get(unit.id()).getTask() == Tasks.NONE && unit.unitType() == UnitType.Worker) {
                        jobMap.put(unit.id(), queue.get(0));
                        queue.remove(0);
                    }
                }
            }

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