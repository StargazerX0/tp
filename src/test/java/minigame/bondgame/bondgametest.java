package minigame.bondgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import player.PlayerProfile;


/**
 * Tests the functionality of bond classes in the EconoCraft game, ensuring that
 * each bond type behaves as expected according to its specifications. This includes
 * validating bond information outputs, interest calculations, and correct interactions
 * with the user interface components managed by the ResponseManager.
 */
class BondTests {

    private PlayerProfile mockPlayerProfile;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the testing environment before each test, which includes preparing
     * a mocked PlayerProfile and redirecting the System output to capture console outputs
     * for validation.
     */
    @BeforeEach
    void setUp() {
        mockPlayerProfile = mock(PlayerProfile.class);
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
    }

    /**
     * Resets the System output to its original setting after each test to ensure
     * that changes do not affect other tests or system operations.
     */
    @BeforeEach
    void tearDown() {
        System.setOut(originalOut); // Reset System.out to its original stream
    }

    /**
     * Tests the methods of Corporate Growth Bond class. This includes verifying the name,
     * price, interest rate, and interest calculation, as well as ensuring the correct
     * output is printed to the console.
     */
    @Test
    void testAllMethodsCorporateGrowthBond() {
        CorporateGrowthBond bond = new CorporateGrowthBond();
        assertEquals("Corporate Growth Bond", bond.returnBondName());
        assertEquals(500, bond.returnBondPrice());
        assertEquals(6, bond.returnBondInterestRate());
        assertEquals(60, bond.calculateInterest(1000));
        bond.printInfo(mockPlayerProfile);
        assertTrue(outContent.toString().contains("Corporate Growth Bond"));
    }

    /**
     * Tests the methods of Government Stability Bond class. Checks the integrity of
     * bond name, pricing, interest rate, interest calculation and the output content.
     */
    @Test
    void testAllMethodsGovernmentStabilityBond() {
        GovernmentStabilityBond bond = new GovernmentStabilityBond();
        assertEquals("Government Stability Bond", bond.returnBondName());
        assertEquals(1000, bond.returnBondPrice());
        assertEquals(3, bond.returnBondInterestRate());
        assertEquals(30, bond.calculateInterest(1000));
        bond.printInfo(mockPlayerProfile);
        assertTrue(outContent.toString().contains("Government Stability Bond"));
    }

    /**
     * Tests all functional aspects of the High Yield Bond class, ensuring that
     * the bond's financial characteristics are correctly implemented and the expected
     * output is presented to the user.
     */
    @Test
    void testAllMethodsHighYieldBond() {
        HighYieldBond bond = new HighYieldBond();
        assertEquals("High Yield Bond", bond.returnBondName());
        assertEquals(100, bond.returnBondPrice());
        assertEquals(10, bond.returnBondInterestRate());
        assertEquals(100, bond.calculateInterest(1000));
        bond.printInfo(mockPlayerProfile);
        assertTrue(outContent.toString().contains("High Yield Bond"));
    }

    /**
     * Validates the functionality of the Inflation Linked Bond class, focusing on
     * accuracy in the calculations that account for both the base interest rate and
     * additional inflation adjustments.
     */
    @Test
    void testAllMethodsInflationLinkedBond() {
        InflationLinkedBond bond = new InflationLinkedBond();
        assertEquals("Inflation Linked Bond", bond.returnBondName());
        assertEquals(1000, bond.returnBondPrice());
        double expectedInterestRate = 3.5; // 2% base + 1.5% inflation rate
        assertEquals(expectedInterestRate, bond.returnBondInterestRate() + 1.5);
        assertEquals(35, bond.calculateInterest(1000));
        bond.printInfo(mockPlayerProfile);
        assertTrue(outContent.toString().contains("Inflation Linked Bond"));
    }
}
