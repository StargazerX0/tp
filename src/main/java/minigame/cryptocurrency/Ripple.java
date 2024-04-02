package minigame.cryptocurrency;

import player.PlayerProfile;
import java.util.Random;

public class Ripple implements CryptoCurrency {
    private static final String NAME = "Ripple";
    private int currentPrice;

    public Ripple() {
        this.currentPrice = 1;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        System.out.println("Crypto Name: " + NAME);
        System.out.println("Current Price: " + currentPrice + " USD");
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();

        int change = random.nextInt(5) - 2;
        this.currentPrice += change;
        return amountInvested * change / 100;
    }

    @Override
    public String returnCryptoName() {
        return NAME;
    }

    @Override
    public int returnCurrentPrice() {
        return currentPrice;
    }
}
