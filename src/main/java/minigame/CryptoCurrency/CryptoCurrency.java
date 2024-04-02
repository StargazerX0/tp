package minigame.CryptoCurrency;

import player.PlayerProfile;

public interface CryptoCurrency {
    public void printInfo(PlayerProfile playerProfile);

    public int calculateChange(int amountInvested);

    public String returnCryptoName();
    
    public int returnCurrentPrice();
}

