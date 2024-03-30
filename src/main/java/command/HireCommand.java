package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class HireCommand implements Command {
    @Override
    public void execute(PlayerProfile playerProfile) throws LockedFeatureException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
