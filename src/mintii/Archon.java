package mintii;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static mintii.RobotPlayer.rc;
import static mintii.Util.moveAway;
import static mintii.Util.randomDirection;
import static mintii.Util.tryMove;

/**
 * Created by clintz on 1/20/2017.
 * Archons are important units that cannot be constructed
 * Each team starts the game with one to three Archons pre-placed on the map
 *     Cannot attack
 *     Can construct Gardeners
 *     Per-turn bytecode limit of 20000
 */
public class Archon {

    static void runArchon() throws GameActionException{
        while (true){
            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
                // Broadcast archon's location for other robots on the team to know
                MapLocation myLocation = rc.getLocation();
                rc.broadcast(0,(int)myLocation.x);
                rc.broadcast(1,(int)myLocation.y);

                // Generate a random direction
                Direction dir = randomDirection();

                // Randomly attempt to build a gardener in this direction
                if (rc.canHireGardener(dir) && Math.random() < .01) {
                    rc.hireGardener(dir);
                    moveAway();
                }

                // Move randomly
                tryMove(randomDirection());

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }
}
