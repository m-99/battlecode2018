// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();
        Direction[] directions = Direction.values();

        while (true) {
            System.out.println("Current round: "+gc.round());
            VecUnit units = gc.myUnits();

            //unit incrementation loop
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                switch(unit.unitType()){
                    case Worker:
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
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }
}