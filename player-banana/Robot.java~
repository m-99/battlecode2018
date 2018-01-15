import bc.*;

import java.util.Arrays;
import java.util.List;

public abstract class Robot {
    public static void randomMove(GameController gc, int id) {
        List<Direction> directions = Arrays.asList(Direction.values());
        while (!directions.isEmpty()) {
            Direction randomDirection = directions.remove((int)(Math.random()*directions.size()));
            if (gc.canMove(id, randomDirection) && gc.isMoveReady(id)) {
                try {
                    gc.moveRobot(id, randomDirection);
                    return;
                } catch (Exception e) {
                    System.out.println("Robot Exception: randomMove");
                }
            }
        }
    }

    public static void directedMove(GameController gc, int id, Direction direction) {
        try {
            gc.moveRobot(id, direction);
        } catch (Exception e) {
            System.out.println("Robot Exception: directedMove");
        }
    }

    public abstract void ability(GameController gc);
}
