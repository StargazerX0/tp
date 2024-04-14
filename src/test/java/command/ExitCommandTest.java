package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExitCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test");
    private final ExitCommand exitCommand = new ExitCommand();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_normalPlayerProfile_printsGoodBye() {
        exitCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("Bye bye adventurer!"));
    }

    @Test
    void isExit_emptyInput_returnsTrue() {
        assertTrue(exitCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsFalse() {
        assertFalse(exitCommand.canGenerateEvent());
    }
}
