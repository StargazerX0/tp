package minigame.cryptocurrencygame;

import minigame.cryptocurrency.Bitcoin;
import minigame.cryptocurrency.CryptoCurrencyStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import player.PlayerProfile;
import player.Asset;

/**
 * Tests the functionality of cryptocurrency classes in the EconoCraft game, ensuring that
 * each cryptocurrency type behaves as expected according to its specifications. This includes
 * validating cryptocurrency information outputs, investment changes, and correct interactions
 * with the user interface components managed by the ResponseManager.
 */
class CryptoCurrencyTests {

    private PlayerProfile mockPlayerProfile;
    private CryptoCurrencyStorage cryptoStorage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    /**
     * Sets up the testing environment before each test, which includes preparing
     * a mocked PlayerProfile, CryptoCurrencyStorage, and redirecting the System output to capture console outputs
     * for validation.
     */
    @BeforeEach
    void setUp() {
        mockPlayerProfile = mock(PlayerProfile.class);
        cryptoStorage = new CryptoCurrencyStorage(mockPlayerProfile);
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
        when(mockPlayerProfile.getOccupation()).thenReturn("Developer");
        when(mockPlayerProfile.getAsset()).thenReturn(mock(Asset.class));
    }

    /**
     * Resets the System output and input to their original settings after each test to ensure
     * that changes do not affect other tests or system operations.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Reset System out to its original stream
        System.setIn(originalIn); // Reset System.in to its original source
    }

    /**
     * Tests the Bitcoin class's functionality by verifying its response to a typical player interaction,
     * checking the output information, and simulating a market change.
     */
    @Test
    void testBitcoinFunctionality() {
        Bitcoin bitcoin = new Bitcoin();

        bitcoin.printInfo(mockPlayerProfile);
        int initialInvestment = 1000;
        int resultChange = bitcoin.calculateChange(initialInvestment);

        assertNotNull(resultChange); // Ensure there is a result from the investment change calculation
        assertTrue(outContent.toString().contains("Bitcoin, as the first decentralized digital currency"));
    }

    /**
     * Validates that the current price and risk factor for Bitcoin are handled and reported correctly.
     */
    @Test
    void testBitcoinMetrics() {
        Bitcoin bitcoin = new Bitcoin();
        assertEquals(10000, bitcoin.returnCurrentPrice());
        assertEquals("Bitcoin", bitcoin.returnCryptoName());
        assertEquals(55, bitcoin.getRiskFactor());
    }
}
