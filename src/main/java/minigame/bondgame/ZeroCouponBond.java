package minigame.bondgame;

import player.PlayerProfile;

public class ZeroCouponBond implements Bond {
    private static final String NAME = "Zero Coupon Bond";
    private static final int PRICE = 800;
    private static final int MATURITY = 10;

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        // 打印债券信息
    }

    @Override
    public int calculateInterest(int amountInvested) {
        if (amountInvested == PRICE) {
            return 1000 - PRICE;
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
