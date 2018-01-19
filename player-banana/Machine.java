import bc.*;

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
                move();
                break;
            case RANDOM_MOVE:
                randomMove();
                break;
            case ATTACK:
                attack();
                break;
            case BLUEPRINT:
                blueprint();
                break;
            case BUILD:
                build();
                break;
            case REPAIR:
                repair();
                break;
            case REPLICATE:
                replicate();
                break;
            case JAVELIN:
                javelin();
                break;
            case SNIPE:
                snipe();
                break;
            case BLINK:
                blink();
                break;
            case HEAL:
                heal();
                break;
            case OVERCHARGE:
                overcharge();
                break;
        }

    }

    //general things
    public void move() {}
    public void randomMove() {}
    public void attack() {}

    //worker things
    public void blueprint() {}
    public void build() {}
    public void repair() {}
    public void replicate() {}

    //knight things
    public void javelin() {}

    //(st)ranger things
    public void snipe() {}

    //mage things
    public void blink() {}

    //healer things
    public void heal() {}
    public void overcharge() {}

}
