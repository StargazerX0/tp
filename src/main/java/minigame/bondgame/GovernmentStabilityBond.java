package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class GovernmentStabilityBond implements Bond {
    private static final String BOND_INFORMATION =
            "The Government Stability Bond is considered a secure investment, " +
                    "with fixed interest payouts and a guaranteed return of principal at maturity. " +
                    "This bond is favored by investors seeking stability and low risk.";
    private static final String NAME = "Government Stability Bond";
    private static final String HIDDEN_INFO =
            "Recent economic trends suggest that the bond market may see a reduction in volatility.";
    private static final int PRICE = 1000;
    private static final int INTEREST_RATE = 3;


    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Price per bond unit: " + PRICE);
        ResponseManager.indentPrint("Annual Interest Rate (%): " + INTEREST_RATE);

        // Display hidden information based on player's occupation
        if (playerProfile.getOccupation().equals("Economist")) {
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


}
