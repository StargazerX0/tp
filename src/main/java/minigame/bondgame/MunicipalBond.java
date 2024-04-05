package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class MunicipalBond implements Bond {
    private static final String BOND_INFORMATION =
            "Municipal Bonds are debt securities issued by a state, municipality, or county to " +
                    "finance its capital expenditures. These bonds are often exempt from federal taxes " +
                    "and sometimes from state and local taxes as well, making them an attractive " +
                    "investment for tax-conscious investors.";
    private static final String NAME = "Municipal Bond";
    private static final String HIDDEN_INFO =
            "There's buzz that the municipality backing this bond is expected to undergo " +
                    "significant infrastructure development, potentially increasing the bond's value.";
    private static final int PRICE = 1000;
    private static final int INTEREST_RATE = 4;
    private static final int MATURITY = 5;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Price per bond unit: " + PRICE);
        ResponseManager.indentPrint("Annual Interest Rate (%): " + INTEREST_RATE);
        ResponseManager.indentPrint("Maturity (years): " + MATURITY);

        // Display hidden information based on player's occupation
        if (playerProfile.getOccupation().equals("Urban Planner") ||
                playerProfile.getOccupation().equals("Public Official")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateInterest(int amountInvested) {
        return (int) (amountInvested * INTEREST_RATE / 100.0);
    }

    @Override
    public String returnBondName() {
        return NAME;
    }

    @Override
    public int returnBondPrice() {
        return PRICE;
    }

    @Override
    public int returnBondInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public int returnBondMaturity() {
        return MATURITY;
    }
}

