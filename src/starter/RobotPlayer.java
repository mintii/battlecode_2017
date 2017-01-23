package starter;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.util.Random;

import static starter.Archon.runArchon;
import static starter.Gardener.runGardener;
import static starter.LumberJack.runLumberjack;
import static starter.Scout.runScout;
import static starter.Soldier.runSoldier;

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
