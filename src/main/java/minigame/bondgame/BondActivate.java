package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;

public class BondActivate {
    public static void start(PlayerProfile playerProfile) throws GameException {
        new BondStorage(playerProfile).play();
    }
}
