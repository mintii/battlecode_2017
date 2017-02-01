package mintii;

import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotInfo;

import static mintii.RobotPlayer.myRand;
import static mintii.RobotPlayer.rc;
import static mintii.Util.wander;

/**
 * Created by bbraxton on 2/1/17.
 */
public class Tank {
    static RobotInfo[] enemyInfo;
    float sensorRadius = rc.getType().sensorRadius;

    public static void runTank() throws GameActionException {
        while (true) {
            if (robotFound()) {
                moveToAndStrikeRobot();
            }
            wander();

            Clock.yield();
        }
    }

    private static boolean robotFound() throws GameActionException {
        enemyInfo = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        if (enemyInfo.length > 0) {
            System.out.println("enemyInfo = " + enemyInfo[0].getLocation());
            return true;
        }
        return false;
    }

    private static void moveToAndStrikeRobot() throws GameActionException {
        MapLocation[] enemyArchonLocations = rc.getInitialArchonLocations(rc.getTeam().opponent());
        MapLocation myArchon = enemyArchonLocations[0];

        if (enemyArchonLocations.length > 1) {
            myArchon = enemyArchonLocations[myRand.nextInt(enemyArchonLocations.length)];
        }

        if (rc.canMove(myArchon)){
            rc.move(myArchon);
        } else if (rc.canMove(enemyInfo[0].getLocation())) {
            rc.move(enemyInfo[0].getLocation());
        }

        if (rc.canFirePentadShot()) {
            rc.firePentadShot(rc.getLocation().directionTo(enemyInfo[0].getLocation()));
        }

    }




}
