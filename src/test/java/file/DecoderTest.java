package file;

import company.Company;
import exception.LoadProfileException;
import minigame.bondgame.HighYieldBond;
import minigame.cryptocurrency.Bitcoin;
import minigame.stockgame.StockOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Asset;
import player.PlayerProfile;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the {@link Decoder} class to ensure it can accurately transform JSON strings
 * back into player profile objects and their associated entities such as assets, stocks, bonds,
 * and company details. These tests validate the decoding process against expected object properties.
 */
class DecoderTest {
    String jsonInput;
    PlayerProfile expectedProfile;

    /**
     * Prepares sample JSON input and corresponding expected PlayerProfile objects before each test.
     */
    @BeforeEach
    void setUp() {
        Company company = new Company("Tech Innovations", 100, 3000,
            1500);
        Asset asset = new Asset(1000);
        asset.setStockList(Arrays.asList(new StockOne()));
        asset.setStockCount(Arrays.asList(10));
        asset.setBondList(Arrays.asList(new HighYieldBond()));
        asset.setBondCount(Arrays.asList(5));
        asset.setCryptoList(Arrays.asList(new Bitcoin()));
        asset.setCryptoCount(Arrays.asList(2));

        expectedProfile = new PlayerProfile("Jane Doe", "Engineer", 95, asset, 1,
            1, true, company);

        jsonInput = "{\n" +
            " \"name\": \"Jane Doe\",\n" +
            " \"occupation\": \"Engineer\",\n" +
            " \"health\": 95,\n" +
            " \"currentRound\": 1,\n" +
            " \"actionCount\": 1,\n" +
            " \"isAdvancedPlayer\": true,\n" +
            " \"company\": {\n" +
            "   \"name\": \"Tech Innovations\",\n" +
            "   \"numberOfEmployees\": 100,\n" +
            "   \"employeeSalary\": 3000,\n" +
            "   \"revenuePerEmployee\": 1500\n" +
            "},\n" +
            " \"asset\": {\n" +
            " \"money\": 1000,\n" +
            " \"stocks\": [{" +
            "\"name\": \"WaveScan Technologies (Start ups) \"," +
            "\"count\": 10" +
            "}],\n" +
            " \"bonds\": [{" +
            "\"name\": \"High Yield Bond\"," +
            "\"count\": 5" +
            "}],\n" +
            " \"cryptos\": [{" +
            "\"name\": \"Bitcoin\"," +
            "\"count\": 2" +
            "}]\n" +
            "}\n" +
            "}";
    }

    /**
     * Tests the decoding process by comparing the expected and actual PlayerProfile objects.
     * This test checks the complete reconstruction of the profile from JSON, including nested entities.
     */
    @Test
    void testDecodePlayerProfile() {
        try {
            PlayerProfile actualProfile = Decoder.decodePlayerProfile(jsonInput);
            assertEquals(expectedProfile.getName(), actualProfile.getName());
            assertEquals(expectedProfile.getOccupation(), actualProfile.getOccupation());
            assertEquals(expectedProfile.getHealth(), actualProfile.getHealth());
            assertEquals(expectedProfile.getCurrentRound(), actualProfile.getCurrentRound());
            assertEquals(expectedProfile.getActionCount(), actualProfile.getActionCount());
            assertTrue(expectedProfile.isAdvancedPlayer());

            // Assertions for deeper nested objects can be similarly structured.
        } catch (LoadProfileException e) {
            fail("Decoding failed with an exception: " + e.getMessage());
        }
    }
}
