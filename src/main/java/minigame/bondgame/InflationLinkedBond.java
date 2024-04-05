package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class InflationLinkedBond implements Bond {
    private static final String BOND_INFORMATION =
            "Inflation Linked Bonds are designed to help protect investors from the " +
                    "declining purchasing power of their money by adjusting the interest rate " +
                    "paid with the rate of inflation. These bonds are typically less risky " +
                    "during periods of high inflation.";
    private static final String NAME = "Inflation Linked Bond";
    private static final String HIDDEN_INFO =
            "Given current market trends, experts anticipate an increase in inflation " +
                    "rates, which could enhance the returns on these bonds in the upcoming years.";
    private static final int PRICE = 1000;
    private static final int BASE_INTEREST_RATE = 2;
    private static final int MATURITY = 7;
    private static final double INFLATION_RATE = 1.5;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Price per bond unit: " + PRICE);
        ResponseManager.indentPrint("Base Annual Interest Rate (%): " + BASE_INTEREST_RATE);
        ResponseManager.indentPrint("Inflation Rate Adjustment (%): " + INFLATION_RATE);
        ResponseManager.indentPrint("Maturity (years): " + MATURITY);

        // Display hidden information based on player's occupation
        if (playerProfile.getOccupation().equals("Economist") ||
                playerProfile.getOccupation().equals("Financial Analyst")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateInterest(int amountInvested) {
        double actualInterestRate = BASE_INTEREST_RATE + INFLATION_RATE;
        return (int) (amountInvested * actualInterestRate / 100.0);
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
        return BASE_INTEREST_RATE;
    }

    @Override
    public int returnBondMaturity() {
        return MATURITY;
    }
}

