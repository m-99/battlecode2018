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
            jobMap.put(unit.id(), new Target());
            unitMap.put(unit.id(), new Machine(gc, unit.id(), unit.unitType(), unit.location().mapLocation(), unit.health(), new Target()));
        }
        VecUnit oldUnits = units;

        boolean phase1Queued = false;
        boolean phase2Queued = false;
        boolean phase3Queued = false;
        int phase = 1;

        while (true) {
            //System.out.println("Current round: "+gc.round());

            oldUnits = units;
            units = gc.myUnits();

            //add babbies
            ArrayList<Unit> babbies = newUnits(oldUnits, units);
            for (int i = 0; i < babbies.size(); i++) {
                Unit unit = babbies.get(i);
                System.out.println("New unit " + unit.id());
                jobMap.put(unit.id(), new Target());
                unitMap.put(unit.id(), new Machine(gc, unit.id(), unit.unitType(), unit.location().mapLocation(), unit.health(), new Target()));
            }

            //phase logic

            if(phase == 1 && !phase1Queued ){
                for(int x = 0; x < 10; x++){
                    //queue.add(new Target(Tasks.RANDOM_MOVE, new MapLocation(Planet.Earth, 0, 0)));
                    queue.add(new Target(Tasks.REPLICATE, Direction.Center));
                }
                phase1Queued = true;
            }else if(phase == 2 && !phase2Queued){

            }else if(phase == 3 && !phase3Queued ){

            }

            //add queue items to open spaces in jobmap
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                System.out.println("" + unitMap.get(unit.id()).getJobStatus());
                //checks if unit is not currently running a job, and updates jobMap with NONE task
                if(unitMap.get(unit.id()).getJobStatus() == TargetStatus.FINISHED){
                    jobMap.put(unit.id(), new Target());
                    System.out.println("Finished job");
                }
                //adds a units Target back to the queue if it failed the job
                else if(unitMap.get(unit.id()).getJobStatus() == TargetStatus.FAILED){
                    queue.add(unitMap.get(unit.id()).getCurrentTarget());
                    jobMap.put(unit.id(), new Target());
                    System.out.println("Failed job");
                }
                if (jobMap.get(unit.id()).getTask() == Tasks.NONE && queue.size() > 0) {
                    //&& isTaskValid(unit.unitType(), queue.get(0).getTask())
                    jobMap.put(unit.id(), queue.get(0));
                    queue.remove(0);
                    System.out.println("Job added to jobMap");
                } else{
                    System.out.println("Job NOT added to jobMap; fuck you");
                }
            }


            /*for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                System.out.println("ID: " + unit.id() + ", job: " + jobMap.get(unit.id()).toString());
            }*/
            for (int i = 0; i < queue.size(); i++) {
                System.out.println("position" + i + " queue: " + queue.get(i).toString());
            }


            //unit incrementation loop
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                unitMap.get(unit.id()).doTarget(jobMap.get(unit.id()));
            }

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

    public static ArrayList newUnits(VecUnit oldUnit, VecUnit newUnit){
        //TODO fix this bullshit
        ArrayList<Unit> babbies = new ArrayList<Unit>();
        for (int i = 0; i < newUnit.size(); i++) {
            boolean inList = false;
            for(int j = 0; j < oldUnit.size(); j++){
                if(newUnit.get(i).id() == oldUnit.get(j).id()) {
                    inList = true;
                }
            }
            if(!inList){
                babbies.add(newUnit.get(i));
                System.out.println("New unit found");
            }
        }
        return babbies;
    }

    public static boolean isTaskValid(UnitType unitType, Tasks task ){
        //TODO:
        /*We want to be able to specify which units specifically attack. Send UnitType with Target?
        Currently only letting knights attack; lol
         */
        switch(unitType){
            case Worker:
                if(task == Tasks.HARVEST || task == Tasks.BLUEPRINT || task == Tasks.BUILD || task == Tasks.REPAIR || task == Tasks.REPLICATE){
                    return true;
                }
                break;
            case Knight:
                if(task == Tasks.JAVELIN || task == Tasks.ATTACK){
                    return true;
                }
                break;
            case Ranger:
                if(task == Tasks.SNIPE){
                    return true;
                }
                break;
            case Mage:
                if(task == Tasks.BLINK){
                    return true;
                }
                break;
            case Healer:
                if(task == Tasks.HEAL || task == Tasks.OVERCHARGE){
                    return true;
                }
                break;
            default:
                if(task == Tasks.MOVE || task == Tasks.RANDOM_MOVE){
                    return true;
                }
                else{
                    return false;
                }
        }
        return false;
    }
}
