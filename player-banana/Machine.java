import bc.*;
import java.util.Arrays;
import java.util.List;

/*
This mess of a class is going to contain all of the methods for any specific task (including path
planning and other memory things). I guess it will also contain the code for the structures, which
will include calculating when the rockets should take off and how the factories build robots,
and that should be built into our Tasks enum. - ur buddy andy
 */
//possible parent class instead of separating robots and structures?
public class Machine {

    GameController gc;
    int id;
    MachineType type;
    MapLocation loc;
    long health;

    public Machine(GameController gamec, int unitId, MachineType ty, MapLocation location, long h) {
        gc = gamec;
        id = unitId;
        type = ty;
        loc = location;
        health = h;
    }

    public void doThings(Target t) {
        switch(t.getTask()) {
            case MOVE:
                move(t.getMapLocation());
                break;
            case RANDOM_MOVE:
                randomMove();
                break;
            case ATTACK:
                attack();
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
    public void randomMove() {}
        List<Direction> directions = Arrays.asList(Direction.values());
        while (!directions.isEmpty()) { //why is isEmpty creating an error???
        Direction randomDirection = directions.remove((int)(Math.random()*directions.size()));
        if (gc.canMove(id, randomDirection) && gc.isMoveReady(id)) {
            try {
                gc.moveRobot(id, randomDirection);
                //return;
            } catch (Exception e) {
                System.out.println("Robot Exception: randomMove");
            }
        }
    }
    public void attack() {}

    //worker things
    public void harvest(Direction d) {
        if(gc.canHarvest(id, d)) {
            try {
                gc.harvest(id, d);
            } catch (Exception e) {
                System.out.println("Robot Exception: harvest");
            }
        }
    }
    public void blueprint(UnitType structure, Direction dir) {
        if(gc.canBlueprint(id, structure, dir)) {
            try {
                gc.blueprint(id, structure, dir);
            } catch (Exception e) {
                System.out.println("Robot Exception: blueprint");
            }
        }
    }
    public void build(int blueprint_id) {
        if(gc.canBuild(id, blueprint_id)) {
            try {
                gc.build(id, blueprint_id);
            } catch (Exception e) {
                System.out.println("Robot Exception: build");
            }
        }
    }
    public void repair(int structure_id) {
        if(gc.canRepair(id, structure_id)) {
            try {
                gc.repair(id, structure_id);
            } catch (Exception e) {
                System.out.println("Robot Exception: repair");
            }
        }
    }
    public void replicate(Direction dir) {
        if(gc.canReplicate(id, dir)) {
            try {
                gc.replicate(id, dir);
            } catch (Exception e) {
                System.out.println("Robot Exception: replicate");
            }
        }
    }

    //knight things
    public void javelin(int target_id) {
        if(gc.canJavelin(id, target_id)) {
            try {
                gc.javelin(id, target_id);
            } catch (Exception e) {
                System.out.println("Robot Exception: javelin");
            }
        }
    }

    //(st)ranger things
    public void snipe(MapLocation location) {
        if(gc.canBeginSnipe(id, location)) {
            try {
                gc.beginSnipe(id, location);
            } catch (Exception e) {
                System.out.println("Robot Exception: snipe");
            }
        }
    }

    //mage things
    public void blink(MapLocation location) {
        if(gc.canBlink(id, location)) {
            try {
                gc.blink(id, location);
            } catch (Exception e) {
                System.out.println("Robot Exception: blink");
            }
        }
    }

    //healer things
    public void heal(int target_id) {
        if(gc.canHeal(id, target_id) {
            try {
                gc.heal(id, target_id);
            } catch (Exception e) {
                System.out.println("Robot Exception: heal");
            }
        }
    }
    public void overcharge(int target_id) {
        if(gc.canOvercharge(id, target_id)) {
            try {
                gc.overcharge(id, target_id);
            } catch (Exception e) {
                System.out.println("Robot Exception: overcharge");
            }
        }
    }

}
