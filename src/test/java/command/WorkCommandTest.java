package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WorkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test");
    private final WorkCommand workCommand = new WorkCommand();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_playerWithWrongInput_printsFailMsg() {
        String simulatedInput = "\n" + "WRONG INPUT\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        workCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("You have failed the typing game and earned nothing."));
    }

    @Test
    void isExit_emptyInput_returnsFalse() {
        assertFalse(workCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsTrue() {
        assertTrue(workCommand.canGenerateEvent());
    }
}
