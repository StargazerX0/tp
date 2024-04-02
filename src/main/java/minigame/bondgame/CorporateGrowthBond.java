package minigame.bondgame;

import player.PlayerProfile;

public class CorporateGrowthBond implements Bond {
    private static final String NAME = "Corporate Growth Bond";
    private static final int PRICE = 500;
    private static final int INTEREST_RATE = 6;
    private static final int MATURITY = 10;

    @Override
    public void printInfo(PlayerProfile playerProfile) {

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
