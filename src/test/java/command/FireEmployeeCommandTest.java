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

class FireEmployeeCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PlayerProfile normalPlayer = new PlayerProfile("test", "test", 100, 100, 1, true);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_fireValidEmployeeNum_printsSuccessMsg() throws GameException {
        normalPlayer.hireEmployee(1);
        FireEmployeeCommand fireEmployeeCommand = new FireEmployeeCommand(1);
        fireEmployeeCommand.execute(normalPlayer);
        assertTrue(outContent.toString().contains("1 of employees has been fired. :("));
    }

    @Test
    void execute_fireInvalidEmployeeNum_throwsGameException() {
        FireEmployeeCommand fireEmployeeCommand = new FireEmployeeCommand(-2);
        assertThrows(GameException.class, () -> fireEmployeeCommand.execute(normalPlayer));
    }

    @Test
    void isExit() {
        FireEmployeeCommand fireEmployeeCommand = new FireEmployeeCommand(1);
        assertFalse(fireEmployeeCommand.isExit());
    }

    @Test
    void canGenerateEvent() {
        FireEmployeeCommand fireEmployeeCommand = new FireEmployeeCommand(1);
        assertTrue(fireEmployeeCommand.canGenerateEvent());
    }
}