package minigame.bondgame;

import player.PlayerProfile;

public class HighYieldBond implements Bond {
    private static final String NAME = "High Yield Bond";
    private static final int PRICE = 100;
    private static final int INTEREST_RATE = 10;
    private static final int MATURITY = 3;

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
