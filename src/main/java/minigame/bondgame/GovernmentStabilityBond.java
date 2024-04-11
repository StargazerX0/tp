package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class GovernmentStabilityBond implements Bond {
    private static final String BOND_INFORMATION =
            "The Government Stability Bond is considered a secure investment, \n" +
                    "with fixed interest payouts and a guaranteed return of principal at maturity. \n" +
                    "This bond is favored by investors seeking stability and low risk. \n" +
                    "It provides fixed return to you every round \n";
    private static final String NAME = "Government Stability Bond";
    private static final int PRICE = 1000;
    private static final int INTEREST_RATE = 3;


    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION + "\n"
        + "Bond Name: " + NAME + "\n"
        + "Price per bond unit: " + PRICE + "\n"
        + "Annual Interest Rate (%): " + INTEREST_RATE + "\n");

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
