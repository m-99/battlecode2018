import bc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
This mess of a class is going to contain all of the methods for any specific task (including path
planning and other memory things). I guess it will also contain the code for the structures, which
will include calculating when the rockets should take off and how the factories build robots,
and that should be built into our Tasks enum. - ur buddy andy
 */
//possible parent class instead of separating robots and structures?
public class Machine {

    //TODO add jobStatus = false condition for each method. For example, in repair method have an IF statement that checks
    //TODO whether the target unit is fully healed. If so, jobStatus = false
    GameController gc;
    int id;
    UnitType type; //instead of machine type?
    MapLocation loc;
    long health;
    TargetStatus jobStatus; //true == currently running Job, false == no job.
    //make jobStatus into a 3-part enum - working, finished, failed. If failed, put job back in queue.
    //needs to be initialized as something, can it be initialized to failed? or should we add a value
    //for none?
    Target currentTarget;

    public Machine(GameController gamec, int unitId, UnitType ty, MapLocation location, long h, Target t) {
        gc = gamec;
        id = unitId;
        type = ty;
        loc = location;
        health = h;
        currentTarget = t;
        jobStatus = TargetStatus.NONE;
    }

    public void doTarget(Target t) {
        jobStatus = TargetStatus.WORKING;
        switch(t.getTask()) {
            case MOVE:
                move(t.getMapLocation());
                break;
            case RANDOM_MOVE:
                randomMove();
                break;
            case ATTACK:
                attack(t.getTargetID());
                break;
            case HARVEST:
                harvest(t.getDirection());
                break;
            case BLUEPRINT:
                blueprint(t.getStructure(), t.getDirection());
                break;
            case BUILD:
                build(t.getTargetID()); //target id can be a blueprint id as well
                break;
            case REPAIR:
                repair(t.getTargetID()); //targets can be structures too
                break;
            case REPLICATE:
                replicate(t.getDirection());
                break;
            case JAVELIN:
                javelin(t.getTargetID());
                break;
            case SNIPE:
                snipe(t.getMapLocation());
                break;
            case BLINK:
                blink(t.getMapLocation());
                break;
            case HEAL:
                heal(t.getTargetID());
                break;
            case OVERCHARGE:
                overcharge(t.getTargetID());
                break;
        }
    }

    //general things
    public void move(MapLocation location) {

    }
    public void randomMove() {
        boolean hasMoved = false;
        ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        //List<Direction> directions = Arrays.asList(Direction.values());
        while (!directions.isEmpty()) {
            Random r = new Random();
            int dirNum = r.nextInt(directions.size());
            Direction randomDir = directions.get(dirNum);
            if (gc.canMove(id, randomDir) && gc.isMoveReady(id)) {
                try {
                    gc.moveRobot(id, randomDir);
                    jobStatus = TargetStatus.FINISHED;
                    hasMoved = true;
                    //return;
                } catch (Exception e) {
                    System.out.println("Robot Exception: randomMove");
                }
            }
            else{
                directions.remove(dirNum);
            }
        }
        System.out.print("randomMove Called!");
        if(hasMoved == false){
            jobStatus = TargetStatus.FINISHED;
        }
    }
    public void attack(int target_id) {
        if(gc.canAttack(id, target_id) && gc.isAttackReady(id)) {
            try {
                gc.attack(id, target_id);
                jobStatus = TargetStatus.FINISHED;
            } catch (Exception e) {
                System.out.println("Robot Exception: javelin");
            }
        }else{
            jobStatus = TargetStatus.FAILED;
        }
    }

    //worker things
    public void harvest(Direction d) {
        if(gc.canHarvest(id, d)) {
            try {
                gc.harvest(id, d);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: harvest");
            }
        }else{
            jobStatus = TargetStatus.FAILED;
        }
    }
    public void blueprint(UnitType structure, Direction dir) {
        if(gc.canBlueprint(id, structure, dir)) {
            try {
                gc.blueprint(id, structure, dir);
                jobStatus = TargetStatus.FINISHED;
            } catch (Exception e) {
                System.out.println("Robot Exception: blueprint");
            }
        }else{
            jobStatus = TargetStatus.FAILED;
        }
    }
    public void build(int blueprint_id) {
        if(gc.canBuild(id, blueprint_id)) {
            try {
                gc.build(id, blueprint_id);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: build");
            }
        }
    }
    public void repair(int structure_id) {
        if(gc.canRepair(id, structure_id)) {
            try {
                gc.repair(id, structure_id);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: repair");
            }
        }
    }
    public void replicate(Direction dir) {  //CENTER gives random direction replication //o cool
        if(dir != Direction.Center) {
            if (gc.canReplicate(id, dir)) {
                try {
                    gc.replicate(id, dir);
                    jobStatus = TargetStatus.FINISHED;
                } catch (Exception e) {
                    System.out.println("Robot Exception: replicate");
                }
            }else{
                jobStatus = TargetStatus.FAILED;
                //System.out.println("reps failed");
            }
        }else{
            boolean hasReplicated = false;
            ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
            while (!directions.isEmpty()) {
                Random r = new Random();
                int dirNum = r.nextInt(directions.size());
                Direction randomDir = directions.get(dirNum);
                if (gc.canReplicate(id, randomDir)) {
                    try {
                        gc.replicate(id, randomDir);
                        jobStatus = TargetStatus.FINISHED;
                        //System.out.println("replicated successfully" + gc.round());
                        hasReplicated = true;
                    } catch (Exception e) {
                        System.out.println("Robot Exception: replicate");
                    }
                }
                else{
                    directions.remove(dirNum);
                }
            }
            if(!hasReplicated){
                jobStatus = TargetStatus.FAILED;
                //System.out.println("reps failed" + gc.round());
            }
        }

    }

    //knight things
    public void javelin(int target_id) {
        if(gc.canJavelin(id, target_id) && gc.isJavelinReady(id)) {
            try {
                gc.javelin(id, target_id);
                jobStatus = TargetStatus.FINISHED;
            } catch (Exception e) {
                System.out.println("Robot Exception: javelin");
            }
        }
    }

    //(st)ranger things
    public void snipe(MapLocation location) {
        if(gc.canBeginSnipe(id, location) && gc.isBeginSnipeReady(id)) {
            try {
                gc.beginSnipe(id, location);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: snipe");
            }
        }
    }

    //mage things
    public void blink(MapLocation location) {
        if(gc.canBlink(id, location) && gc.isBlinkReady(id)) {
            try {
                gc.blink(id, location);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: blink");
            }
        }
    }

    //healer things
    public void heal(int target_id) {
        if(gc.canHeal(id, target_id) && gc.isHealReady(id)) {
            try {
                gc.heal(id, target_id);
                //return IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: heal");
            }
        }
    }
    public void overcharge(int target_id) {
        if(gc.canOvercharge(id, target_id) && gc.isOverchargeReady(id)) {
            try {
                gc.overcharge(id, target_id);
                //jobStatus IF statement
            } catch (Exception e) {
                System.out.println("Robot Exception: overcharge");
            }
        }
    }

    public TargetStatus getJobStatus() {
        return jobStatus;
    }
    public Target getCurrentTarget() {
        return currentTarget;
    }

    public void setJobStatus(TargetStatus status) { jobStatus = status; }
    public void setCurrentTarget(Target t) { currentTarget = t; }

}
