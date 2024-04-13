package file;

import company.Company;
import minigame.bondgame.HighYieldBond;
import minigame.cryptocurrency.Bitcoin;
import minigame.stockgame.StockOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Asset;
import player.PlayerProfile;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the {@link Serializer} class to ensure it correctly serializes player profiles,
 * including associated entities such as assets, stocks, bonds, and companies, into a JSON formatted string.
 * These tests verify the output against a predefined expected JSON structure.
 */
class SerializerTest {
    private PlayerProfile playerProfile;

    /**
     * Prepares a PlayerProfile object with comprehensive nested structures (company, assets)
     * before each test. This setup reflects typical real-world usage scenarios for the serializer.
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
        playerProfile = new PlayerProfile("Jane Doe", "Engineer", 95, asset, 1,
            1, true, company);
    }

    /**
     * Verifies that the JSON string produced by the Serializer accurately represents
     * the PlayerProfile object's state. This test ensures that every attribute from complex
     * nested objects to simple fields is correctly serialized.
     */
    @Test
    void testConstructJson() {
        String expected = "{\n" +
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
        String actualJson = Serializer.constructJson(playerProfile).trim();
        assertEquals(expected, actualJson, "The JSON output should match the expected format.");
    }
}

