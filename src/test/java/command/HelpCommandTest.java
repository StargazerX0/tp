package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(new PlayerProfile("test", "test"));
        assertTrue(outContent.toString().contains("Here are the list of commands:")
                && outContent.toString().contains("help - Show the list of commands")
                && outContent.toString().contains("bye - to exit"));
    }

    @Test
    void isExit() {
        HelpCommand helpCommand = new HelpCommand();
        assertFalse(helpCommand.isExit());
    }

    @Test
    void canGenerateEvent() {
        HelpCommand helpCommand = new HelpCommand();
        assertFalse(helpCommand.canGenerateEvent());
    }
}
