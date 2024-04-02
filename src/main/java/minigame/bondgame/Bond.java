package minigame.bondgame;

import player.PlayerProfile;

public interface Bond {
    public void printInfo(PlayerProfile playerProfile);

    public int calculateInterest(int amountInvested);

    public String returnBondName();

    public int returnBondPrice();

    public int returnBondInterestRate();
    
    public int returnBondMaturity(); 
}

