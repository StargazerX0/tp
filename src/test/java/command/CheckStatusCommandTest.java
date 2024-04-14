package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.RESET;

class CheckStatusCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test");

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_playerProfile_printsHelp() {
        CheckStatusCommand checkStatusCommand = new CheckStatusCommand();
        checkStatusCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("Current Status:")
                && outContent.toString().contains("Your name is: test")
                && outContent.toString().contains("|" + GREEN + "#".repeat(10) + RESET + "|"));
    }
}
