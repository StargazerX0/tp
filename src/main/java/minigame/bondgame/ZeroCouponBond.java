package minigame.bondgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class ZeroCouponBond implements Bond {
    private static final String BOND_INFORMATION =
            "Zero Coupon Bonds are a type of bond that does not pay interest during its life. " +
                    "Instead, it is sold at a discount to its face value and matures at that face value, " +
                    "providing a profit to the holder. These bonds can offer a substantial return to " +
                    "investors willing to lock in their money for a longer period.";
    private static final String NAME = "Zero Coupon Bond";
    private static final String HIDDEN_INFO =
            "Given the current low-interest rate environment, Zero Coupon Bonds are increasingly " +
                    "attractive, as they lock in higher returns compared to regular bonds.";
    private static final int PRICE = 800; // Purchase price
    private static final int MATURITY = 10; // Maturity in years
    private static final int FACE_VALUE = 1000; // The value at which the bond matures

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(BOND_INFORMATION);
        ResponseManager.indentPrint("Bond Name: " + NAME);
        ResponseManager.indentPrint("Purchase Price: " + PRICE);
        ResponseManager.indentPrint("Face Value at Maturity: " + FACE_VALUE);
        ResponseManager.indentPrint("Maturity (years): " + MATURITY);

        // Display hidden information based on player's insights or occupation
        if (playerProfile.getOccupation().equals("Economist") ||
                playerProfile.getOccupation().equals("Financial Planner")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateInterest(int amountInvested) {
        if (amountInvested >= PRICE) {
            return FACE_VALUE - PRICE;
        }
        return 0;
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
        return 0;
    }

    @Override
    public int returnBondMaturity() {
        return MATURITY;
    }
}

