package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class HighYieldBond implements Bond {
    private static final String BOND_INFORMATION =
            "As the name suggests, the High Yield Bond comes with a high-interest rate, \n" +
                    "indicating a higher risk but offering substantial income potential. This type \n" +
                    "of bond is suited for investors with a high-risk tolerance seeking significant \n" +
                    "returns. \n" +
                    "It provides fixed return to you every round \n";;
    private static final String NAME = "High Yield Bond";
    private static final int PRICE = 100;
    private static final int INTEREST_RATE = 10;

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
