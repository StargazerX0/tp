package minigame.bondgame;

import player.PlayerProfile;

public class MunicipalBond implements Bond {
    private static final String NAME = "Municipal Bond";
    private static final int PRICE = 1000;
    private static final int INTEREST_RATE = 4;
    private static final int MATURITY = 5;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        // 打印债券信息
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
