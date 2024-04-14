package command;

import exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.RESET;

class AdjustSalaryCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test", 100, 100, 1, true);
    private final AdjustSalaryCommand lowerSalaryCommand = new AdjustSalaryCommand("lower", 100);
    private final AdjustSalaryCommand raiseSalaryCommand = new AdjustSalaryCommand("raise", 100);
    private final AdjustSalaryCommand raiseSalaryCommandExceedLimit = new AdjustSalaryCommand("raise", 1000);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_lowerSalaryCommand_printsSuccessMsg() throws GameException {
        lowerSalaryCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("You have successfully lowered the salary by $"
                + RED + "100" + RESET + ".\n"));
    }

    @Test
    void execute_raiseSalaryCommand_printsSuccessMsg() throws GameException {
        raiseSalaryCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("You have successfully raised the salary by $"
                + GREEN + "100" + RESET + ".\n"));
    }

    @Test
    void execute_raiseSalaryCommandExceedLimit_throwsGameException() {
        assertThrows(GameException.class, () -> raiseSalaryCommandExceedLimit.execute(playerProfile));
    }
}
