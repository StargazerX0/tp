package minigame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HangmanTest {
    private Hangman hangman;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        hangman = new Hangman();
        System.setOut(new PrintStream(outContent));  // Redirect system output to capture for verification
    }

    @Test
    void testStartGame_InvalidInputs() {
        String data = "123\na\nb\nc\nd\n";  // Assuming the game may prompt for input up to 5 times
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        hangman.startGame();

        // Capture and check that the game prompts again after invalid inputs and handles a valid input
        String output = outContent.toString();
        assertTrue(output.contains("Invalid input. Please guess exactly one letter at a time:"),
            "Should warn about invalid input");
    }
}