package minigame.CryptoCurrency;

import player.PlayerProfile;
import java.util.Random;

public class Polkadot implements CryptoCurrency {
    private static final String NAME = "Polkadot";
    private int currentPrice;

    public Polkadot() {
        this.currentPrice = 30;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        System.out.println("Crypto Name: " + NAME);
        System.out.println("Current Price: " + currentPrice + " USD");
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(8) - 4;
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
