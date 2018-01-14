import bc.*;

public class Player {
    public static void main(String[] args) {
        GameController gc = new GameController();

        while (true) {
            VecUnit units = gc.myUnits();

            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);
                if (unit.unitType().equals(UnitType.Worker)) {
                    for (Direction direction : Direction.values()) {
                         if (gc.canReplicate(unit.id(), direction) && gc.karbonite() >= 15) {
                             gc.replicate(unit.id(), direction);
                         }
                    }
                }
            }
            gc.nextTurn();
        }
    }
}
