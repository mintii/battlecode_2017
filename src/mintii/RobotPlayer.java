package mintii;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.util.Random;

import static mintii.Archon.runArchon;
import static mintii.Gardener.runGardener;
import static mintii.LumberJack.runLumberjack;
import static mintii.Scout.runScout;
import static mintii.Soldier.runSoldier;

public strictfp class RobotPlayer {
    static RobotController rc;
    static Random myRand;

    public static void run(RobotController rc) throws GameActionException {
        myRand = new Random(rc.getID());
        RobotPlayer.rc = rc;
        switch (rc.getType()) {
            case ARCHON:
                runArchon();
                break;
            case GARDENER:
                runGardener();
                break;
            case SOLDIER:
                runSoldier();
                break;
            case LUMBERJACK:
                runLumberjack();
                break;
            case SCOUT:
                runScout();
                break;
        }
	}


}
