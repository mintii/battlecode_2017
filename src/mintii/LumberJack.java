package mintii;

import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.RobotInfo;
import battlecode.common.TreeInfo;

import static mintii.RobotPlayer.rc;
import static mintii.Util.donateBullets;
import static mintii.Util.wander;

/**
 * Created by clintz on 1/20/2017.
 */
public class LumberJack {
    static TreeInfo[] treeInfos;
    static RobotInfo[] enemyInfo;

    static void runLumberjack() throws GameActionException{
        while (true){
            if (robotFound()) {
                moveToAndStrikeRobot();
            } else if (treeFound()) {
                moveToAndChopTree();
            } else if (rc.getRoundNum() > 1500){
                rc.disintegrate();
            } else {
                wander();
            }

            if (rc.getRoundNum() > 10) {
                donateBullets();
            }
            Clock.yield();

        }
    }

    private static boolean treeFound() throws GameActionException {
        treeInfos = rc.senseNearbyTrees();
        if ((treeInfos.length > 0) && !treeInfos[0].getTeam().isPlayer()){
            return true;
        }
        return false;
    }

    private static void moveToAndChopTree() throws GameActionException {
        if (rc.canMove(treeInfos[0].getLocation())){
            rc.move(treeInfos[0].getLocation());
        }

        if (rc.canShake(treeInfos[0].getLocation())){
            rc.shake(treeInfos[0].getLocation());
        }

        if (!rc.canShake(treeInfos[0].getLocation())){
            //If you can't shake, then chop!
            if (rc.canChop(treeInfos[0].getLocation())){
                rc.chop(treeInfos[0].getLocation());
            }
        }
    }

    private static boolean robotFound() throws GameActionException {
        enemyInfo = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        if (enemyInfo.length > 0){
            return true;
        }
        return false;
    }

    private static void moveToAndStrikeRobot() throws GameActionException{
        if (rc.canMove(enemyInfo[0].getLocation())){
            rc.move(enemyInfo[0].getLocation());
        }
        if (rc.canStrike()){
            rc.strike();
        }
    }
}

