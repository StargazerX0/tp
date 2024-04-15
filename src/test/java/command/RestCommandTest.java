package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RestCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test");
    private final PrintStream originalOut = System.out;
    private final RestCommand restCommand = new RestCommand();

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
        String simulatedInput = "WRONG INPUT\n" + "T";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        restCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("You did not input T or F, which " +
                "is what you should be inputting. \n" +
                "Should I give you another chance to input again? Hell nah, get used to it!!!"));
    }

    @Test
    void execute_playerWithCorrectInput() {
        String simulatedInput = "T\n" + "T";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        restCommand.execute(playerProfile);
        assertTrue(outContent.toString().contains("Correct!")
                || outContent.toString().contains("Incorrect!"));
    }

    @Test
    void isExit_emptyInput_returnsFalse() {
        assertFalse(restCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsTrue() {
        assertTrue(restCommand.isAnAction());
    }

}
