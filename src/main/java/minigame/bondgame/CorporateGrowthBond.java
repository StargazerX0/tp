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
                    "investors looking for growth potential in the corporate sector.\n";
    private static final String NAME = "Corporate Growth Bond";
    private static final String HIDDEN_INFO =
            "Insider news suggests that the issuing corporation is on the brink of a \n" +
                    "major breakthrough that could significantly increase its market value. \n";
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
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Price per bond unit: " + PRICE);
        ResponseManager.indentPrint("Annual Interest Rate (%): " + INTEREST_RATE);


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


}
