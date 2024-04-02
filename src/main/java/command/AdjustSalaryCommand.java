package command;

import exception.GameException;
import player.PlayerProfile;
import ui.ResponseManager;

public class AdjustSalaryCommand implements Command {
    private final String updateType;
    private int amount;

    public AdjustSalaryCommand(String updateType, int amount) {
        this.updateType = updateType;
        this.amount = amount;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (amount > playerProfile.getEmployeeSalary()) {
            throw new GameException("Extent of salary adjustment cannot be more than the employee's salary.\n");
        }
        if (updateType.equals("lower")) {
            amount = -amount;
            ResponseManager.indentPrint("You have successfully lowered the salary by $" + amount + ".\n");
        } else {
            ResponseManager.indentPrint("You have successfully raised the salary by $" + amount + ".\n");
        }
        playerProfile.updateSalary(amount);
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
