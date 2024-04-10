package command;

import exception.GameException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class AdjustSalaryCommand implements Command {
    private static final int ADJUSTMENT_LIMIT = 800;
    private final String updateType;
    private int amount;

    public AdjustSalaryCommand(String updateType, int amount) {
        this.updateType = updateType;
        this.amount = amount;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (updateType.equals("lower")) {
            if (amount > playerProfile.getEmployeeSalary()) {
                throw new GameException("Extent of lowering salary cannot be more than the employee's salary.\n");
            }
            amount = -amount;
            indentPrint("You have successfully lowered the salary by -$" + amount + ".\n");
        } else {
            if (amount > ADJUSTMENT_LIMIT) {
                throw new GameException("Extent of rising salary cannot be more than $" + ADJUSTMENT_LIMIT + ".\n");
            }
            indentPrint("You have successfully raised the salary by $" + amount + ".\n");
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
