package randomevent;

import player.PlayerProfile;

/**
 * Represents a negative event that can happen in the game
 * to harm the player in terms of their health or asset.
 */
public class NegativeEvent extends RandomEvent {
    private static final String[] NEGATIVE_EVENT = {
        "Economy crisis!",
        "You got sick!",
        "You lost your wallet!",
        "Company employees are not satisfied with their salary!"
    };

    public NegativeEvent(double probability) {
        super(probability);
    }


    /**
     * {@inheritDoc}
     *
     * Triggers a negative event to happen randomly to the player.
     *
     * @param playerProfile the player's profile that the event will affect
     */
    @Override
    public void triggerEvent(PlayerProfile playerProfile) {
        int range = playerProfile.isAdvancedPlayer() ?
                NEGATIVE_EVENT.length : NEGATIVE_EVENT.length - 1;
        int index = playerProfile.getHealth() <= 20 ? 1 :
                playerProfile.getEmployeeSalary() <= 700 ? 3 :
                (int)(Math.random() * range);
        switch (index) {
        case 0:
            economyCrisis(playerProfile);
            break;

        case 1:
            gotSick(playerProfile);
            break;

        case 2:
            loseWallet(playerProfile);
            break;

        case 3:
            employeeRiot(playerProfile);
            break;

        default:
            System.out.println("A peaceful round!");
        }
    }

    private static void employeeRiot(PlayerProfile playerProfile) {
        System.out.println(NEGATIVE_EVENT[3]);
        playerProfile.updateRevenue(-200);
        System.out.println("The revenue per employee has been decreased by $200!");
    }

    private static void economyCrisis(PlayerProfile playerProfile) {
        System.out.println(NEGATIVE_EVENT[0]);
        playerProfile.adjustAssetMultiplier(0.8);
        System.out.println("Your money received has been decreased by 20% for this round!");
    }

    private static void gotSick(PlayerProfile playerProfile) {
        System.out.println(NEGATIVE_EVENT[1]);
        playerProfile.setHealth(50);
        System.out.println("You have lost $4000 to pay for medical bills.\n" +
                "Your health has been set to 50%.\n" +
                "Take care of your health by EXERCISE or REST!");
        playerProfile.loseAsset(4000);
    }

    private static void loseWallet(PlayerProfile playerProfile) {
        System.out.println(NEGATIVE_EVENT[2]);
        playerProfile.loseAsset(1000);
    }
}
