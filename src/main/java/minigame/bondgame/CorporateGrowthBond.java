package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class CorporateGrowthBond implements Bond {
    private static final String BOND_INFORMATION =
            "The Corporate Growth Bond offers a higher interest rate to compensate for the " +
                    "increased risk associated with corporate debt. This bond is attractive to " +
                    "investors looking for growth potential in the corporate sector.";
    private static final String NAME = "Corporate Growth Bond";
    private static final String HIDDEN_INFO =
            "Insider news suggests that the issuing corporation is on the brink of a " +
                    "major breakthrough that could significantly increase its market value.";
    private static final int PRICE = 500;
    private static final int INTEREST_RATE = 6;
    private static final int MATURITY = 10;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Price per bond unit: " + PRICE);
        ResponseManager.indentPrint("Annual Interest Rate (%): " + INTEREST_RATE);
        ResponseManager.indentPrint("Maturity (years): " + MATURITY);

        // Display hidden information based on player's occupation
        if (playerProfile.getOccupation().equals("Financial Analyst")) {
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
