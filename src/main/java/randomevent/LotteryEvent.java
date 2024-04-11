package randomevent;

import player.PlayerProfile;
import static ui.Parser.isAccept;

/**
 * Represents a lottery event that can happen in the game which allows the player to win a cash prize.
 */
public class LotteryEvent extends RandomEvent {
    private static final int TICKET_PRICE = 2000;
    private static final int[] PRIZES = {0, 100, 500, 800, 1000, 1500, 2500, 3000, 10000};
    public LotteryEvent(double probability) {
        super(probability);
    }

    /**
     * {@inheritDoc}
     *
     * Triggers a lottery event to happen randomly to the player.
     *
     * @param playerProfile the player's profile that the event will affect
     */
    @Override
    public void triggerEvent(PlayerProfile playerProfile) {
        System.out.println("You have entered a lottery event! \n" +
                "You can choose to buy a ticket for $2000. \n" +
                "If you win, you will receive a cash prize up to $10000! \n" +
                "Do you want to buy a ticket? (Yes/No)");
        if (isAccept()) {
            drawLottery(playerProfile);
        } else {
            System.out.println("You have chosen not to buy a ticket.");
        }
    }

    /**
     * Simulates the process of buying a lottery ticket and drawing a prize.
     * The player will lose $2000 for buying a ticket and may win a cash prize up to $10000.
     *
     * @param playerProfile the player's profile that the lottery will affect
     */
    private void drawLottery(PlayerProfile playerProfile) {
        playerProfile.loseAsset(TICKET_PRICE);
        System.out.println("You have bought a ticket for $2000.");
        int prizeIndex = (int)(Math.random() * PRIZES.length);
        int prize = PRIZES[prizeIndex];
        if (prize == 0) {
            System.out.println("You have won nothing. Better luck next time!");
            return;
        }
        System.out.println("You have won $" + prize + "!");
        playerProfile.addAsset(prize);
    }
}
