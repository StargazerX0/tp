package minigame.cryptocurrency;

import player.PlayerProfile;
import java.util.Random;

public class Bitcoin implements CryptoCurrency {
    private static final String NAME = "Bitcoin";
    private int currentPrice;

    public Bitcoin() {
        this.currentPrice = 10000; 
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        System.out.println("Crypto Name: " + NAME);
        System.out.println("Current Price: " + currentPrice + " USD");
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(20) - 10;
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
