package minigame.stockgame;

import player.PlayerProfile;

public interface Stock {

    public void printInfo(PlayerProfile playerProfile);

    public int investmentGain(int stockAmount);

    public String returnStockName();

    public int returnStockPrice();

}
