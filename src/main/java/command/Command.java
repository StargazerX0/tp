package command;

import exception.GameException;
import player.PlayerProfile;

public interface Command {
    void execute(PlayerProfile playerProfile) throws GameException;
    boolean isExit();
}
