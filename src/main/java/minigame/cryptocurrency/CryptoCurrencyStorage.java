package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CryptoCurrencyStorage {
    private final List<CryptoCurrency> cryptosAvailable = new ArrayList<>();
    private final PlayerProfile playerProfile;
    private boolean completeTrade = false;


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
     */
    public void play() {
        while (!completeTrade) {
            if (cryptosAvailable.isEmpty()) {
                setUp();
            }
            System.out.println("Select a cryptocurrency to invest in:");
            for (int i = 0; i < cryptosAvailable.size(); i++) {
                System.out.println((i + 1) + ": " + cryptosAvailable.get(i).returnCryptoName());
            }
            Scanner scanner = new Scanner(System.in);
            try {
                int choice = Integer.parseInt(scanner.nextLine()) - 1;

                if (choice >= 0 && choice < cryptosAvailable.size()) {
                    CryptoCurrency current = cryptosAvailable.get(choice);
                    current.printInfo(playerProfile);

                    System.out.println("How much in USD do you want to invest in " + current.returnCryptoName() +
                            "? Input 0 if you want none");
                    int response = Integer.parseInt(scanner.nextLine());
                    if (response >= current.returnCurrentPrice() && playerProfile.getAsset().getAsset() >= response) {
                        int changeAmount = current.calculateChange(response);
                        playerProfile.getAsset().deductAsset(response);
                        playerProfile.getAsset().addCrypto(current, response);

                        System.out.println("You've successfully invested " + response +
                                " USD in " + current.returnCryptoName() + ".");
                        System.out.println("Market changes resulted in a " + (changeAmount >= 0 ? "gain" : "loss") +
                                " of $" + Math.abs(changeAmount) + ".");
                        completeTrade = true;
                    } else {
                        ResponseManager.indentPrint("Invalid input or insufficient funds.\n");
                    }
                } else {
                    ResponseManager.indentPrint("Invalid selection. Please select a valid cryptocurrency.\n");
                }
            } catch(IllegalArgumentException e) {
                ResponseManager.indentPrint("Please input a valid message!\n");
                completeTrade = false;
            }
        }
    }
}