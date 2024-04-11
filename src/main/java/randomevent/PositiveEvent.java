package randomevent;

import player.PlayerProfile;

import java.util.Random;

/**
 * Represents a positive event that can happen in the game
 * to benefit the player.
 */
class PositiveEvent extends RandomEvent {
    private static final String[] POSITIVE_EVENT = {
        "Your boss gave you a bonus!",
        "Your had a great dinner with your family!",
        "Due to pandemic, the government gave you subsidy of $1000!",
        "Your company products sold very well this month!"
    };

    public PositiveEvent(double probability) {
        super(probability);
    }

    /**
     * {@inheritDoc}
     *
     * Triggers a positive event to happen randomly to the player.
     *
     * @param playerProfile the player's profile that the event will affect
     */
    @Override
    public void triggerEvent(PlayerProfile playerProfile) {
        int range = playerProfile.isAdvancedPlayer() ?
                POSITIVE_EVENT.length : POSITIVE_EVENT.length - 1;
        int index = new Random().nextInt(range);
        switch (index) {
        case 0:
            System.out.println(POSITIVE_EVENT[0]);
            playerProfile.adjustAssetMultiplier(1.1);
            System.out.println("Your money received has been increased by 10% for the next round!");
            break;

        case 1:
            System.out.println(POSITIVE_EVENT[1]);
            playerProfile.addHealth(5);
            System.out.println("Your health has been increased by 5!");
            break;

        case 2:
            System.out.println(POSITIVE_EVENT[2]);
            playerProfile.addAsset(1000);
            break;

        case 3:
            System.out.println(POSITIVE_EVENT[3]);
            playerProfile.updateRevenue(100);
            System.out.println("The revenue per employee has been increased by $100!");
            break;

        default:
            System.out.println("A peaceful round!");
        }
    }
}
