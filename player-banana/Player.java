// import the API.
// See xxx for the javadocs.
import bc.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/*TODO:
deal with all of this polymorphism bs. One solution is to add every single possible method
(Including getters and setters? ew) to the interface, but that might just defeat the purpose.
Have a method of testing this, I will add it to the github (called Oops). Might just need to
sleep on this/brainstorm another solution with y'all. UnitMap idea might have to be scrapped idk.
Maybe we have to make our own map class like the example from the previous year. We can figure it
out! - andy
*/
public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();
        Direction[] directions = Direction.values();

        //strategy state variables:
        boolean init_worker_pop = true;

        ArrayList<Target> queue = new ArrayList<Target>();
        HashMap<Integer, Target> jobMap = new HashMap<Integer, Target>();
        //changed Machine to an interface to allow for the correct doTarget method to be called in each object
        HashMap<Integer, Machine> unitMap = new HashMap<Integer, Machine>();
        //ArrayList<Object> unitList = new ArrayList<Object>();

        //define units, add units to unitList, and add initial units to jobMap
        VecUnit units = gc.myUnits();
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            jobMap.put(unit.id(), new Target(Tasks.NONE, unit.location().mapLocation(), unit.unitType()));
            unitMap.put(unit.id(), new Worker(gc, unit.id(), unit.location().mapLocation(), unit.health()));
        }


        while (true) {
            System.out.println("Current round: "+gc.round());

            units = gc.myUnits();
            //note: don't need to mark dead units; simply ignoring them in job hashmap for now

            //phase logic
            boolean phase1Queued = false;
            boolean phase2Queued = false;
            boolean phase3Queued = false;
            int phase = 1;

            if(phase == 1 && !phase1Queued ){
                for(int x = 0; x < 10; x++){
                    queue.add(new Target(Tasks.MOVE, new MapLocation(Planet.Earth, 0, 0), UnitType.Worker));
                }
            }else if(phase == 2 && !phase2Queued){

            }else if(phase == 3 && !phase3Queued ){

            }

            //add queue items to open spaces in Hashmap
            if(queue.size() > 0){
                for (int i = 0; i < units.size(); i++) {
                    Unit unit = units.get(i);
                    if (jobMap.get(unit.id()).getTask() == Tasks.NONE && unit.unitType() == queue.get(0).getUnitType()) {
                        jobMap.put(unit.id(), queue.get(0));
                        queue.remove(0);
                    }
                }
            }

            //unit incrementation loop
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                unitMap.get(unit.id()).doTarget(jobMap.get(unit.id()));
            }

            /*//unit incrementation loop
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                if(unit.location().isOnPlanet(Planet.Earth)){
                    switch(unit.unitType()){
                        case Worker:
                            //TODO
                            //make work with objects
                            //make replication stuff work, by adding to unitList
                            //not using vecUnit?

                            /*Random r = new Random();
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

            }*/
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    public static int getWorkers(VecUnit units){
        int count = 0;
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            //if((unit instanceof Worker) && (((Worker) o).name() == name)){
            //    count++;
            //}
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
