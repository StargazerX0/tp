package command;

import minigame.Hangman;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import player.PlayerProfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link ExerciseCommand} class.
 * Ensures that the ExerciseCommand correctly handles game selection and updates player profiles based on game outcomes.
 */
class ExerciseCommandTest {

    private ExerciseCommand command;
    private PlayerProfile playerProfile;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    void setUp() {
        command = new ExerciseCommand();
        playerProfile = Mockito.mock(PlayerProfile.class);
        ByteArrayInputStream testIn = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(testIn);
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * Tests whether the command correctly executes the game choice process and triggers game play for Hangman.
     */
    @Test
    void execute_validChoiceForHangman_triggersHangmanGame() {
        Hangman mockHangman = mock(Hangman.class);
        command = new ExerciseCommand() {
            @Override
            public void playHangman(PlayerProfile profile) {
                mockHangman.startGame();  // Simulate starting the game
                profile.addHealth(10);    // Simulate outcome
            }
        };

        command.execute(playerProfile);
        verify(mockHangman, times(1)).startGame();
        verify(playerProfile, times(1)).addHealth(10);
    }

    /**
     * Tests the getGameChoice method to ensure it only accepts valid choices and returns the correct game choice.
     */
    @Test
    void getGameChoice_validInput_returnsCorrectChoice() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);
        assertEquals(1, command.getGameChoice(), "Should return 1 for Tic Tac Toe choice");
    }

}
