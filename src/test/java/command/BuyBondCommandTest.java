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
 * Unit tests for the {@link BuyBondCommand} class.
 * Ensures that the bond purchasing command behaves correctly under various conditions.
 */
class BuyBondCommandTest {

    private PlayerProfile mockProfile;
    private BuyBondCommand buyBondCommand;

    /**
     * Initializes mock objects and the test subject before each test.
     */
    @BeforeEach
    void setUp() {
        mockProfile = mock(PlayerProfile.class);
        buyBondCommand = new BuyBondCommand();
    }

    /**
     * Verifies that executing the BuyBondCommand as a non-advanced player
     * results in a LockedFeatureException being thrown. This test ensures that
     * bond purchasing is restricted to advanced players.
     */
    @Test
    void execute_nonAdvancedPlayer_throwsLockedFeatureException() {
        when(mockProfile.isAdvancedPlayer()).thenReturn(false);
        assertThrows(LockedFeatureException.class, () -> buyBondCommand.execute(mockProfile),
                "Executing BuyBondCommand as a non-advanced player should throw LockedFeatureException.");
    }

    /**
     * Ensures that the {@code isExit} method of BuyBondCommand always returns false,
     * indicating that this command does not terminate the game session.
     */
    @Test
    void isExit_always_returnsFalse() {
        assertFalse(buyBondCommand.isExit(),
                "isExit should always return false for BuyBondCommand.");
    }

    /**
     * Tests that the {@code canGenerateEvent} method of BuyBondCommand always returns true,
     * indicating that executing this command can potentially trigger an in-game event.
     */
    @Test
    void canGenerateEvent_always_returnsTrue() {
        assertTrue(buyBondCommand.canGenerateEvent(),
                "canGenerateEvent should always return true for BuyBondCommand, indicating the possibility of triggering an event.");
    }
}
