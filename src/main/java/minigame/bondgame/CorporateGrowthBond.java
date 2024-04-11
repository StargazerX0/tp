package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents a Corporate Growth Bond in the EconoCraft game. This type of bond is designed
 * to offer a higher interest rate, reflecting the increased risk and potential for growth
 * in the corporate sector.
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
     * Prints detailed information about this Corporate Growth Bond, including any special
     * hidden information based on the player's occupation.
     *
     * @param playerProfile The player's profile, used to determine if hidden information should be displayed.
     */
    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION + "\n");
        ResponseManager.indentPrint("Bond Name: " + NAME + "\n");
        ResponseManager.indentPrint("Price per bond unit: " + PRICE + "\n");
        ResponseManager.indentPrint("Annual Interest Rate (%): " + INTEREST_RATE + "\n");

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
