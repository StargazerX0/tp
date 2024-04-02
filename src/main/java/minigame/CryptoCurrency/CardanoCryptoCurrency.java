package minigame.CryptoCurrency;

import player.PlayerProfile;
import java.util.Random;

public class Cardano implements CryptoCurrency {
    private static final String NAME = "Cardano";
    private int currentPrice;

    public Cardano() {
        this.currentPrice = 2; 
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        System.out.println("Crypto Name: " + NAME);
        System.out.println("Current Price: " + currentPrice + " USD");
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(4) - 2;
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
