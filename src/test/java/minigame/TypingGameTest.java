package minigame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

class TypingGameTest {

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
    void testTypingGame_correctTyping_returnFullAccuracy() {
        TypingGame typingGame = new TypingGame("The quick brown fox jumps over the lazy dog.");
        // Simulate user input enter
        String simulatedInput = "\n";

        simulatedInput += "The quick brown fox jumps over the lazy dog.\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        typingGame.startGame();
        assertEquals(100, typingGame.getAccuracy());
    }

    @Test
    void testTypingGame_typoWithCorrectNumOfCharacters_returnPartialAccuracy() {
        TypingGame typingGame = new TypingGame("The quick brown fox jumps over the lazy dog.");
        // Simulate user input enter
        String simulatedInput = "\n";

        simulatedInput += "The quick brown fox jumps over the lazy cat.\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        typingGame.startGame();
        assertEquals(93, typingGame.getAccuracy());
    }

    @Test
    void testTypingGame_typoWithExtraCharacters_returnPartialAccuracy() {
        TypingGame typingGame = new TypingGame("The quick brown fox jumps over the lazy dog.");
        // Simulate user input enter
        String simulatedInput = "\n";

        simulatedInput += "The quiick   brown fox jumps   over the lazy dog.\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        typingGame.startGame();
        assertEquals(88, typingGame.getAccuracy());
    }

    @Test
    void testTypingGame_typoWithMissingCharacters_returnPartialAccuracy() {
        TypingGame typingGame = new TypingGame("The quick brown fox jumps over the lazy dog.");
        // Simulate user input enter
        String simulatedInput = "\n";

        simulatedInput += "The quick rown fox jumps over the lazy.\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        typingGame.startGame();
        assertEquals(77, typingGame.getAccuracy());
    }
}
