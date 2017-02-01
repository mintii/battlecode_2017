package mintii;

import battlecode.common.*;

import static mintii.RobotPlayer.myRand;
import static mintii.RobotPlayer.rc;

/**
 * Created by clintz on 1/17/2017.
 */
public class Util {

    public static Direction randomDirection() {
        return(new Direction(myRand.nextFloat()*2*(float)Math.PI));
    }

    /**
     * Attempts to move in a given direction, while avoiding small obstacles directly in the path.
     *
     * @param dir The intended direction of movement
     * @return true if a move was performed
     * @throws GameActionException
     */
    static boolean tryMove(Direction dir) throws GameActionException {

        return tryMove(dir,20,3);
    }

    public static void wander() throws GameActionException {
        Direction dir = randomDirection();
        tryMove(dir);
    }

    static void donateBullets() throws GameActionException {
        if(rc.getTeamBullets() > 250){
            rc.donate(100);
        }
    }

    static void moveAway() throws GameActionException {
        MapLocation location = rc.getLocation();
        RobotInfo[] rInformation = rc.senseNearbyRobots(-1, rc.getTeam());
        if(rInformation.length > 0){
            // There's at least one robot near me, and I want to move away from them.
            if(rc.canMove(rc.getLocation().directionTo(rInformation[0].getLocation()).opposite())){
                rc.move(rc.getLocation().directionTo(rInformation[0].getLocation()).opposite());
            }
        }
    }
    /**
     * Attempts to move in a given direction, while avoiding small obstacles direction in the path.
     *
     * @param dir The intended direction of movement
     * @param degreeOffset Spacing between checked directions (degrees)
     * @param checksPerSide Number of extra directions checked on each side, if intended direction was unavailable
     * @return true if a move was performed
     * @throws GameActionException
     */
    static boolean tryMove(Direction dir, float degreeOffset, int checksPerSide) throws GameActionException {

        // First, try intended direction
        if (!rc.hasMoved() && rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }
        // Now try a bunch of similar angles
        //boolean moved = rc.hasMoved();
        int currentCheck = 1;

        while(currentCheck<=checksPerSide) {
            // Try the offset of the left side
            if(!rc.hasMoved() && rc.canMove(dir.rotateLeftDegrees(degreeOffset*currentCheck))) {
                rc.move(dir.rotateLeftDegrees(degreeOffset*currentCheck));
                return true;
            }
            // Try the offset on the right side
            if(! rc.hasMoved() && rc.canMove(dir.rotateRightDegrees(degreeOffset*currentCheck))) {
                rc.move(dir.rotateRightDegrees(degreeOffset*currentCheck));
                return true;
            }
            // No move performed, try slightly further
            currentCheck++;
        }

        // A move never happened, so return false.
        return false;
    }


    static void hireScout(Direction hireDir) throws GameActionException {
        if (rc.canBuildRobot(RobotType.SCOUT, hireDir)) {
            rc.buildRobot(RobotType.SCOUT, hireDir);
        }
    }

    static void hireLumberJack(Direction hireDir) throws GameActionException {
        if (rc.canBuildRobot(RobotType.LUMBERJACK, hireDir)){
            rc.buildRobot(RobotType.LUMBERJACK, hireDir);
        }
    }

    static void hireSoldier(Direction hireDir) throws GameActionException {
        if (rc.canBuildRobot(RobotType.SOLDIER, hireDir)){
            rc.buildRobot(RobotType.SOLDIER, hireDir);
        }
    }
}
