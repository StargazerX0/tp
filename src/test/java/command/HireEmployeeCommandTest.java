package command;

import exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HireEmployeeCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerWithEnoughMoney =
            new PlayerProfile("test", "test", 100, 10000, 1, true);
    private final PlayerProfile playerWithoutEnoughMoney =
            new PlayerProfile("test", "test", 100, 100, 1, true);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_playerWithEnoughMoney_printsSuccessMsg() throws GameException {
        HireEmployeeCommand hireEmployeeCommand = new HireEmployeeCommand(2);
        System.setOut(new PrintStream(outContent));
        hireEmployeeCommand.execute(playerWithEnoughMoney);
        assertTrue(outContent.toString().contains("2 of employees has been hired. :)"));
    }

    @Test
    void execute_playerWithoutEnoughMoney_throwsGameException() {
        HireEmployeeCommand hireEmployeeCommand = new HireEmployeeCommand(2);
        assertThrows(GameException.class, () -> hireEmployeeCommand.execute(playerWithoutEnoughMoney));
    }

    @Test
    void execute_invalidEmployeeNumber_throwsGameException() {
        HireEmployeeCommand invalidEmployeeNumberCommand = new HireEmployeeCommand(-1);
        assertThrows(GameException.class, () -> invalidEmployeeNumberCommand.execute(playerWithEnoughMoney));
    }

    @Test
    void isExit() {
        HireEmployeeCommand hireEmployeeCommand = new HireEmployeeCommand(2);
        assertFalse(hireEmployeeCommand.isExit());
    }

    @Test
    void canGenerateEvent() {
        HireEmployeeCommand hireEmployeeCommand = new HireEmployeeCommand(2);
        assertTrue(hireEmployeeCommand.canGenerateEvent());
    }
}