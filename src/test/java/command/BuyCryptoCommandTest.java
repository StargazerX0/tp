package command;

import exception.LockedFeatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link BuyCryptoCommand} class.
 * This class tests the behavior of the BuyCryptoCommand, especially focusing on its
 * restrictions, exit condition, and event generation capability.
 */
class BuyCryptoCommandTest {

    private PlayerProfile mockProfile;
    private BuyCryptoCommand buyCryptoCommand;

    /**
     * Prepares the testing environment by initializing mock objects and the test subject
     * before each test execution.
     */
    @BeforeEach
    void setUp() {
        // Create a mock PlayerProfile for use in tests to simulate different player conditions.
        mockProfile = mock(PlayerProfile.class);
        // Instantiate the BuyCryptoCommand to be tested.
        buyCryptoCommand = new BuyCryptoCommand();
    }

    /**
     * Verifies that attempting to execute the BuyCryptoCommand as a non-advanced player
     * results in a LockedFeatureException, enforcing the rule that only advanced players can
     * buy cryptocurrency.
     */
    @Test
    void executeNonAdvancedPlayerThrowsLockedFeatureException() {
        // Simulate a non-advanced player scenario.
        when(mockProfile.isAdvancedPlayer()).thenReturn(false);
        // Verify that executing the command under these conditions throws the expected exception.
        assertThrows(LockedFeatureException.class,
                () -> buyCryptoCommand.execute(mockProfile),
                "Attempting to buy cryptocurrency as " +
                        "a non-advanced player should throw a LockedFeatureException.");
    }

    /**
     * Ensures that the {@code isExit} method of BuyCryptoCommand consistently returns false,
     * indicating that this command does not signal the end of the game.
     */
    @Test
    void isExitAlwaysReturnsFalse() {
        // Verify that isExit always returns false for the BuyCryptoCommand.
        assertFalse(buyCryptoCommand.isExit(),
                "The isExit method should always return false, " +
                        "indicating that the BuyCryptoCommand does not terminate the game session.");
    }

    /**
     * Tests that the {@code canGenerateEvent} method of the BuyCryptoCommand always returns true,
     * suggesting that executing this command may trigger an in-game event.
     */
    @Test
    void canGenerateEventAlwaysReturnsTrue() {
        // Verify that canGenerateEvent always returns true for the BuyCryptoCommand.
        assertTrue(buyCryptoCommand.canGenerateEvent(),
                "The canGenerateEvent method should always return true, " +
                        "indicating the possibility of an event being generated upon execution.");
    }
}
