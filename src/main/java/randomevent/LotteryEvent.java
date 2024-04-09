package randomevent;

import java.util.Scanner;

import exception.CommandInputException;
import player.PlayerProfile;
import static ui.Parser.isAccept;


public class LotteryEvent extends RandomEvent {
    private static final int TICKET_PRICE = 2000;
    private static final String yesRegex = "(?i)(y|yes)";
    private static final String noRegex = "(?i)(n|no)";
    private static final int[] PRIZES = {0, 100, 1000, 2000, 2500, 3000, 10000};
    public LotteryEvent(double probability) {
        super(probability);
    }

    @Override
    public void triggerEvent(PlayerProfile playerProfile) throws CommandInputException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("You have entered a lottery event! \n" +
                "You can choose to buy a ticket for $2000. \n" +
                "If you win, you will receive a cash prize up to $10000! \n" +
                "Do you want to buy a ticket? (Y/N)");
        String response = userInput.nextLine();
        if (isAccept(response)) {
            drawLottery(playerProfile);
        } else {
            System.out.println("You have chosen not to buy a ticket.");
        }
    }

    private void drawLottery(PlayerProfile playerProfile) {
        playerProfile.loseAsset(TICKET_PRICE);
        System.out.println("You have bought a ticket for $2000.");
        int prizeIndex = (int)(Math.random() * PRIZES.length);
        int prize = PRIZES[prizeIndex];
        playerProfile.addAsset(prize);
        if (prize == 0) {
            System.out.println("You have won nothing. Better luck next time!");
            return;
        }
        System.out.println("You have won $" + prize + "!");
    }
}
