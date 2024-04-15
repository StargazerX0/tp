package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the collection of available cryptocurrencies for players to invest in within the game.
 * This class handles the initialization of available cryptocurrencies and facilitates the player's
 * interaction with the investment process, including selecting a cryptocurrency, investing a specified
 * amount of USD, and simulating the outcome of such investments based on market fluctuations.
 */
public class CryptoCurrencyStorage {
    private final List<CryptoCurrency> cryptosAvailable = new ArrayList<>();
    private final PlayerProfile playerProfile;
    private boolean completeTrade = false;

    /**
     * Constructs a new CryptoCurrencyStorage with a specified player profile.
     * This constructor initializes the available cryptocurrencies for investment.
     *
     * @param playerProfile The profile of the player engaging in cryptocurrency investments.
     */
    public CryptoCurrencyStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }

    /**
     * Initializes the list of available cryptocurrencies. This method is called upon creation
     * of the CryptoCurrencyStorage instance and populates the list with various types of cryptocurrencies.
     */
    private void setUp() {
        cryptosAvailable.add(new Bitcoin());
        cryptosAvailable.add(new Ethereum());
        cryptosAvailable.add(new Litecoin());
        cryptosAvailable.add(new Cardano());
    }

    /**
     * Facilitates the cryptocurrency investment process for the player. This method displays the list
     * of available cryptocurrencies, handles the player's selection, and processes the investment based
     * on the player's input. It simulates market changes and updates the player's assets accordingly.
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
                    System.out.println("Please input a value larger than the crypto price because you are no longer" +
                            " inputting quantities, you are just inputting the amount of money you want to invest in");
                    int response = Integer.parseInt(scanner.nextLine());
                    if (response == 0) {
                        completeTrade = true;
                        break;
                    }
                    if (response >= current.returnCurrentPrice() &&
                            playerProfile.getAsset().outputMoney() >= response) {
                        playerProfile.getAsset().deductAsset(response);
                        playerProfile.getAsset().addCrypto(current, response);

                        System.out.println("You've successfully invested " + response +
                                " USD in " + current.returnCryptoName() + ".");
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
