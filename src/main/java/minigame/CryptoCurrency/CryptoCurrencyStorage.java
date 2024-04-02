package minigame.CryptoCurrency;

import exception.GameException;
import player.PlayerProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CryptoCurrencyStorage {
    private final List<CryptoCurrency> cryptosAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private final PlayerProfile playerProfile;

    public CryptoCurrencyStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }

    private void setUp() {
        cryptosAvailable.add(new Bitcoin());
        cryptosAvailable.add(new Ethereum());
        cryptosAvailable.add(new Litecoin());
        cryptosAvailable.add(new Cardano());
        cryptosAvailable.add(new Polkadot());
        cryptosAvailable.add(new Ripple());
    }

    public void play() throws GameException {
        if (cryptosAvailable.isEmpty()) {
            setUp();
        }
        Scanner scanner = new Scanner(System.in);
        int index = getRandomNumber(0, cryptosAvailable.size() - 1);
        CryptoCurrency current = cryptosAvailable.get(index);
        current.printInfo(playerProfile);
    
        while (!completeTrade) {
            try {
                System.out.println("How much in USD do you want to invest in " + current.returnCryptoName() + "? Input 0 if you want none");
                int response = Integer.parseInt(scanner.nextLine());
                if (response < 0) {
                    throw new GameException("Invalid input: Please input a number greater than or equal to 0");
                }
                if (response == 0) {
                    completeTrade = true;
                    System.out.println("No cryptocurrency purchased.");
                } else {
                    if (playerProfile.getAsset().getAsset() >= response) {
                        int quantity = response / current.returnCurrentPrice();
                        playerProfile.getAsset().deductAsset(response);
                        playerProfile.getAsset().addCrypto(current, quantity);
                        completeTrade = true;
                        System.out.println("You've successfully invested " + response + " USD in " + current.returnCryptoName() + " and received " + quantity + " units.");
                    } else {
                        throw new GameException("Insufficient funds: You cannot afford to invest this amount.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a valid amount in USD.");
            } catch (GameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
