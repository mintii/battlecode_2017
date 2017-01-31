package mintii;

import battlecode.common.*;

import static battlecode.common.RobotType.LUMBERJACK;
import static battlecode.common.RobotType.SOLDIER;
import static mintii.RobotPlayer.rc;
import static mintii.Util.*;

/**
 * Created by clintz on 1/20/2017.
 */
public class Gardener {
    static int treeToAttendTo = 0;
    static int movesNorth = 0;
    static int buildLocationModifier = 0; //0 is West

    public static void runGardener() throws GameActionException {
        while (true) {
            createFlower();
            Clock.yield();
        }
    }

    private static void moveNorth() throws GameActionException {
        if (rc.canMove(Direction.getNorth())) {
            rc.move(Direction.getNorth());
        }
    }

    private static void createFlower() throws GameActionException {
        MapLocation ml = rc.getLocation().add((GameConstants.BULLET_TREE_RADIUS + .045f) * (treeToAttendTo + buildLocationModifier + 1), 2);
        Direction plantDir = rc.getLocation().directionTo(ml);
        rc.setIndicatorLine(rc.getLocation(), ml, 0, 0, 255);
        MapLocation hireLoc = rc.getLocation().add((GameConstants.BULLET_TREE_RADIUS + .045f) * buildLocationModifier, 5);
        Direction hireDir = rc.getLocation().directionTo(hireLoc);
        rc.setIndicatorLine(rc.getLocation(), hireLoc, 0, 0, 0);
       if(rc.getRoundNum() % 100 == 0){
            hireScout(hireDir);
        } else if (rc.getRoundNum() % 5 == 0 && rc.getRoundNum() < 450) {
            hireLumberJack(hireDir);
        } else if (rc.getRoundNum() % 5 == 0) {
            hireSoldier(hireDir);
        }
        if (rc.canInteractWithTree(ml)) {
            if (rc.canWater(ml)) {
                rc.water(ml);
                donateBullets();
            }
        } else if (rc.canPlantTree(plantDir)) {
            rc.plantTree(plantDir);
        }
        treeToAttendTo++;
        if (treeToAttendTo > 4) {
            treeToAttendTo = 0;
        }
    }
}