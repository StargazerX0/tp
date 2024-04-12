package command;

import exception.LockedFeatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link BuyBondCommand}.
 */
class BuyBondCommandTest {

    private PlayerProfile mockProfile;
    private BuyBondCommand buyBondCommand;

    /**
     * Sets up common objects before each test.
     */
    @BeforeEach
    void setUp() {
        mockProfile = mock(PlayerProfile.class);
        buyBondCommand = new BuyBondCommand();
    }


    /**
     * Tests that executing the command as a non-advanced player throws LockedFeatureException.
     */
    @Test
    void execute_NonAdvancedPlayer_ThrowsLockedFeatureException() {
        when(mockProfile.isAdvancedPlayer()).thenReturn(false);
        assertThrows(LockedFeatureException.class, () -> buyBondCommand.execute(mockProfile));
    }

    /**
     * Verifies that {@code isExit} method always returns false.
     */
    @Test
    void isExit_Always_ReturnsFalse() {
        assertFalse(buyBondCommand.isExit());
    }

    /**
     * Verifies that {@code canGenerateEvent} method always returns true.
     */
    @Test
    void canGenerateEvent_Always_ReturnsTrue() {
        assertTrue(buyBondCommand.canGenerateEvent());
    }
}
