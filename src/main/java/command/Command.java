package command;

import player.PlayerProfile;
import exception.GameException;
public interface Command {
    void execute(PlayerProfile playerProfile) throws GameException;
    boolean canGenerateEvent();
    boolean isExit();
}
