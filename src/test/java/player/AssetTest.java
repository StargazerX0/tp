package player;


import minigame.bondgame.HighYieldBond;
import minigame.cryptocurrency.Litecoin;
import minigame.stockgame.StockOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the {@link Asset} class.
 * Verifies that the financial assets management functionalities work as expected.
 */
class AssetTest {

    private Asset asset;

    /**
     * Setup common test data and configurations.
     */
    @BeforeEach
    void setUp() {
        asset = new Asset(5000);
    }

    /**
     * Test that the outputMoney method correctly returns the current total asset.
     */
    @Test
    void outputAsset() {
        assertEquals(5000, asset.getAsset(),
                "The outputMoney method should return the current total asset.");
    }

    /**
     * Test adding a specific amount to the total asset.
     */
    @Test
    void testAddAsset() {
        asset.addAsset(1000);
        assertEquals(6000, asset.getAsset(),
                "Adding 1000 to the asset should increase total asset to 6000.");
    }

    /**
     * Test deducting a specific amount from the total asset.
     */
    @Test
    void testDeductAsset() {
        asset.deductAsset(1000);
        assertEquals(4000, asset.getAsset(),
                "Deducting 1000 from the asset should decrease total asset to 4000.");
    }


    /**
     * Test that the asset is marked as achieved when reaching the final goal.
     */
    @Test
    void testIsAchieved() {
        Asset highAsset = new Asset(1000000);
        assertTrue(highAsset.isAchieved(),
                "Asset should be marked as achieved when reaching the final goal.");
    }

    @Test
    void testAsset_checkBuyStock() {
        Asset testAsset = new Asset(5000);
        StockOne testStock = new StockOne();
        testAsset.addStock(testStock, 10);
        assertFalse(testAsset.getStockList().isEmpty());
    }

    @Test
    void testAsset_buyDuplicateStock() {
        Asset testAssetDup = new Asset(5000);
        StockOne testStock = new StockOne();
        testAssetDup.addStock(testStock, 10);
        testAssetDup.addStock(testStock, 20);
        assertEquals(testAssetDup.getStockCount().size(), 1);
    }

    @Test
    void testAsset_checkBondStorage() {
        Asset testAsset = new Asset(5000);
        HighYieldBond testBond = new HighYieldBond();
        testAsset.addBond(testBond, 10);
        assertFalse(testAsset.getBondList().isEmpty());
    }

    @Test
    void testAsset_checkBondReturn() {
        HighYieldBond testBond = new HighYieldBond();
        asset.addBond(testBond, 10);
        assertTrue(asset.bondReturn() > 100);
    }

    @Test
    void testAsset_checkCryptoReturn() {
        Litecoin testCrypto = new Litecoin();
        asset.addCrypto(testCrypto, 1000);
        assertTrue((asset.bondReturn() == 0 ) || (asset.bondReturn() > 100));
    }

    /**
     * Test that the asset is considered bankrupt when the total asset is 0 or less.
     */
    @Test
    void testIsBankrupt() {
        Asset bankruptAsset = new Asset(0);
        assertTrue(bankruptAsset.isBankrupt(),
                "Asset should be considered bankrupt when the total asset is 0 or less.");
    }
}
