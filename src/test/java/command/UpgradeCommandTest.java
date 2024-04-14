package command;

import exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpgradeCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;
    private final UpgradeCommand upgradeCommand = new UpgradeCommand();
    private final PlayerProfile playerWithJustEnoughMoney =
            new PlayerProfile("test", "test", 100,
                    10001, 1, false);
    private final PlayerProfile playerWithNotEnoughMoney =
            new PlayerProfile("test", "test", 100,
                    10000, 1, false);
    private final PlayerProfile upgradedPlayer =
            new PlayerProfile("test", "test", 100,
                    100, 5, true);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_playerWithJustEnoughMoney_printsSuccessMsg() throws GameException {
        upgradeCommand.execute(playerWithJustEnoughMoney);
        assertTrue(outContent.toString().contains("You have successfully upgraded your player!"));
    }

    @Test
    void execute_playerWithNotEnoughMoney_throwsMoneyNotEnoughException() {
        assertThrows(GameException.class, () -> upgradeCommand.execute(playerWithNotEnoughMoney));
    }

    @Test
    void execute_upgradedPlayer_throwsGameException() {
        assertThrows(GameException.class, () -> upgradeCommand.execute(upgradedPlayer));
    }

    @Test
    void isExit_emptyInput_returnsFalse() {
        assertFalse(upgradeCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsFalse() {
        assertFalse(upgradeCommand.canGenerateEvent());
    }
}
