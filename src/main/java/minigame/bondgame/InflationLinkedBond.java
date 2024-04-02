package minigame.bondgame;

import player.PlayerProfile;

public class InflationLinkedBond implements Bond {
    private static final String NAME = "Inflation Linked Bond";
    private static final int PRICE = 1000;
    private static final int BASE_INTEREST_RATE = 2;
    private static final int MATURITY = 7;
    private static final double INFLATION_RATE = 1.5;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
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
