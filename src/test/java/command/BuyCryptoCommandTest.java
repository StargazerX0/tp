package command;

import exception.GameException;
import exception.LockedFeatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BuyCryptoCommand} class.
 */
class BuyCryptoCommandTest {

    private PlayerProfile mockProfile;
    private BuyCryptoCommand buyCryptoCommand;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockProfile = mock(PlayerProfile.class);
        buyCryptoCommand = new BuyCryptoCommand();
    }


    /**
     * Tests that the execute method throws a LockedFeatureException when the player is not an advanced player.
     */
    @Test
    void execute_NonAdvancedPlayer_ThrowsLockedFeatureException() {
        when(mockProfile.isAdvancedPlayer()).thenReturn(false);
        assertThrows(LockedFeatureException.class, () -> buyCryptoCommand.execute(mockProfile));
    }

    /**
     * Tests that the isExit method of the BuyCryptoCommand always returns false.
     */
    @Test
    void isExit_Always_ReturnsFalse() {
        assertFalse(buyCryptoCommand.isExit());
    }

    /**
     * Tests that the canGenerateEvent method of the BuyCryptoCommand always returns true.
     */
    @Test
    void canGenerateEvent_Always_ReturnsTrue() {
        assertTrue(buyCryptoCommand.canGenerateEvent());
    }
}
