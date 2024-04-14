package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class HireEmployeeCommand implements Command {
    private static final int HIRE_COST_PER_EMPLOYEE = 1000;
    private final int hireNumber;

    public HireEmployeeCommand(int hireNumber) {
        this.hireNumber = hireNumber;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        if (hireNumber < 0) {
            throw new GameException("Invalid employee number.\n");
        }
        if (hireNumber * HIRE_COST_PER_EMPLOYEE > playerProfile.getAsset().getAsset()) {
            throw new GameException("Insufficient funds to hire employees. " +
                    "Each hiring cost 1000\n");
        }
        playerProfile.loseAsset(hireNumber * HIRE_COST_PER_EMPLOYEE);
        playerProfile.hireEmployee(hireNumber);
        indentPrint(hireNumber + " of employees has been hired. :)\n");
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
