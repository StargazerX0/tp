package minigame.cryptocurrency;

import exception.GameException;
import player.PlayerProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CryptoCurrencyStorage {
    private final List<CryptoCurrency> cryptosAvailable = new ArrayList<>();
    private final PlayerProfile playerProfile;


    /**
     * The CryptoCurrencyStorage class manages the available cryptocurrencies
     * for investment and handles player interactions for investing in cryptocurrencies.
     */
    public CryptoCurrencyStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }


    /**
     * Initializes the list of available cryptocurrencies for investment.
     */
    private void setUp() {
        cryptosAvailable.add(new Bitcoin());
        cryptosAvailable.add(new Ethereum());
        cryptosAvailable.add(new Litecoin());
        cryptosAvailable.add(new Cardano());
    }


    /**
     * Executes the investment process, allowing the player to select a cryptocurrency
     * and invest a specified amount of USD. It simulates market changes and adjusts the player's assets.
     *
     * @throws GameException if there is an issue during the investment process.
     */
    public void play() throws GameException {
        if (cryptosAvailable.isEmpty()) {
            setUp();
        }
        System.out.println("Select a cryptocurrency to invest in:");
        for (int i = 0; i < cryptosAvailable.size(); i++) {
            System.out.println((i + 1) + ": " + cryptosAvailable.get(i).returnCryptoName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        if (choice >= 0 && choice < cryptosAvailable.size()) {
            CryptoCurrency current = cryptosAvailable.get(choice);
            current.printInfo(playerProfile);

            System.out.println("How much in USD do you want to invest in " + current.returnCryptoName() +
                    "? Input 0 if you want none");
            int response = Integer.parseInt(scanner.nextLine());
            if (response > 0 && playerProfile.getAsset().getAsset() >= response) {
                int changeAmount = current.calculateChange(response);
                playerProfile.getAsset().deductAsset(response);
                playerProfile.getAsset().addAsset(changeAmount);

                System.out.println("You've successfully invested " + response +
                        " USD in " + current.returnCryptoName() + ".");
                System.out.println("Market changes resulted in a " + (changeAmount >= 0 ? "gain" : "loss") +
                        " of $" + Math.abs(changeAmount) + ".");
            } else {
                System.out.println("Invalid input or insufficient funds.");
            }
        } else {
            System.out.println("Invalid selection. Please select a valid cryptocurrency.");
        }
    }
}
