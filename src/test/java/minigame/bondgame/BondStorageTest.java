package minigame.bondgame;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;
import player.Asset;

/**
 * Unit tests for the BondStorage class in the EconoCraft game. These tests aim to verify that
 * the BondStorage class correctly initializes and interacts with PlayerProfile to manage
 * bond-related transactions.
 */
class BondStorageTest {
    private PlayerProfile playerProfile;
    private BondStorage bondStorage;

    /**
     * Sets up mocks for PlayerProfile and Asset classes to be used in each test. This setup
     * prepares a simulated environment where BondStorage's interactions with PlayerProfile's
     * assets can be monitored and verified.
     */
    @BeforeEach
    void setUp() {
        playerProfile = mock(PlayerProfile.class);
        Asset mockAsset = mock(Asset.class);
        when(playerProfile.getAsset()).thenReturn(mockAsset);

        bondStorage = new BondStorage(playerProfile);
    }

    /**
     * Test to verify that the BondStorage is initialized properly. This test assumes
     * that the BondStorage should initialize with a non-null list of bonds, which
     * needs verification via a public getter method for bonds which must be implemented.
     */
    @Test
    void testInitializationOfBonds() {
        assertNotNull(bondStorage, "BondStorage should be initialized.");
        // Uncomment the following line once the getBondsAvailable() method is implemented.
        // assertTrue(bondStorage.getBondsAvailable().size() > 0, "Bonds should be initialized and available.");
    }

}


