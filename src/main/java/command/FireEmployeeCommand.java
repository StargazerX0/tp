package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

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
        if (fireNumber < 0 || fireNumber > playerProfile.getNumberOfEmployees()) {
            throw new GameException("Invalid employee number.\n");
        }

        playerProfile.fireEmployee(fireNumber);
        indentPrint(fireNumber + "of employees has been fired. :(\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean canGenerateEvent() {
        return true;
    }
}
