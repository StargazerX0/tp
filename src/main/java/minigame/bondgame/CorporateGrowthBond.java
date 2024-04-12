package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents a Corporate Growth Bond within the EconoCraft game, characterized by its higher
 * interest rates reflecting the potential for growth and increased risk in the corporate sector.
 * This class implements the {@code Bond} interface, providing specific details and calculations
 * relevant to this type of bond.
 */
public class CorporateGrowthBond implements Bond {
    private static final String BOND_INFORMATION =
            "The Corporate Growth Bond offers a higher interest rate to compensate for the \n" +
                    "increased risk associated with corporate debt. This bond is attractive to \n" +
                    "investors looking for growth potential in the corporate sector.\n" +
                    "It provides fixed return to you every round \n";
    private static final String NAME = "Corporate Growth Bond";
    private static final int PRICE = 500;
    private static final int INTEREST_RATE = 6;

    /**
     * Provides detailed information about the Corporate Growth Bond, including its name,
     * price per unit, and annual interest rate. Depending on the player's profile, additional
     * hidden information might also be displayed.
     *
     * @param playerProfile The profile of the player, used to determine the scope of information displayed.
     */
    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION + "\n"
            + "Bond Name: " + NAME + "\n"
            + "Price per bond unit: " + PRICE + "\n"
            + "Annual Interest Rate (%): " + INTEREST_RATE + "\n");

    }

    /**
     * Calculates the interest earned on a specified investment amount in this bond, based on
     * its annual interest rate.
     *
     * @param amountInvested The amount of money invested in the bond.
     * @return The calculated interest earned on the investment.
     */
    @Override
    public int calculateInterest(int amountInvested) {

        return (int) (amountInvested * INTEREST_RATE / 100.0);
    }

    /**
     * Returns the name of this Corporate Growth Bond.
     *
     * @return The name of the bond.
     */
    @Override
    public String returnBondName() {

        return NAME;
    }

    /**
     * Returns the purchase price for a single unit of this bond.
     *
     * @return The price per unit of the bond.
     */
    @Override
    public int returnBondPrice() {

        return PRICE;
    }

    /**
     * Returns the annual interest rate of this bond.
     *
     * @return The interest rate of the bond.
     */
    @Override
    public int returnBondInterestRate() {

        return INTEREST_RATE;
    }
}
