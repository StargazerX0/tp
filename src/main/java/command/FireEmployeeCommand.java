package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import ui.ResponseManager;

public class FireEmployeeCommand implements Command {
    private final int fireNumber;

    public FireEmployeeCommand(int fireNumber) {
        this.fireNumber = fireNumber;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        if (fireNumber < 0 || fireNumber >= playerProfile.getNumberOfEmployees()) {
            throw new GameException("Invalid employee number.\n");
        }

        playerProfile.fireEmployee(fireNumber);
        ResponseManager.indentPrint(fireNumber + "of employees has been fired. :(\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
