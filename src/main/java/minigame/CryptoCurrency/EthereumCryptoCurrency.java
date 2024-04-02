package minigame.CryptoCurrency;

import player.PlayerProfile;
import java.util.Random;

public class Ethereum implements CryptoCurrency {
    private static final String NAME = "Ethereum";
    private int currentPrice;

    public Ethereum() {
        this.currentPrice = 2500;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        System.out.println("Crypto Name: " + NAME);
        System.out.println("Current Price: " + currentPrice + " USD");
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(15) - 7;
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
