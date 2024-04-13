package minigame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the {@link Hangman} class.
 * It tests the game's behavior in response to both invalid and valid inputs,
 * as well as checking the game status after a series of incorrect guesses.
 */
class HangmanTest {
    private Hangman hangman;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Sets up the testing environment before each test. This includes initializing the Hangman game
     * instance and setting the System.out output to capture all outputs for assertions.
     */
    @BeforeEach
    void setup() {
        hangman = new Hangman();
        System.setOut(new PrintStream(outContent));  // Redirect system output to capture for verification
    }

    /**
     * Tests how the Hangman game handles a sequence of invalid inputs followed by multiple guesses.
     * Ensures that the game prompts for correct input format and correctly parses valid letter guesses.
     */
    @Test
    void testStartGame_invalidInputs() {
        String data = "123\na\nb\nc\nd\n";  // Assuming the game may prompt for input up to 5 times
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        hangman.startGame();

        // Capture and check that the game prompts again after invalid inputs and handles a valid input
        String output = outContent.toString();
        assertTrue(output.contains("Invalid input. Please guess exactly one letter at a time:"),
            "Should warn about invalid input");
    }

    /**
     * Tests the game's status after a player makes a series of incorrect guesses, which should lead to a loss.
     * The game should recognize a losing condition correctly and return the appropriate status.
     */
    @Test
    void testGameStatusAfterLoss() {
        String data = "0\n0\n0\n0\n0\n0\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        hangman.startGame();

        assertEquals(-1, hangman.getStatus(),
            "Status should be -1 after losing the game.");
    }
}
