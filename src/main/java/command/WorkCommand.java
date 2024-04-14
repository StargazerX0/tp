package command;

import minigame.TypingGame;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class WorkCommand implements Command {
    private static final int SALARY = 2500;
    private static final int HEALTH_LOSS = 20;
    private static final int PASS_ACCURACY = 50;
    private static final int PERCENT_RATIO = 100;

    /**
     * {@inheritDoc}
     *
     * Executes the work command by starting the typing game and updating the player's profile.
     *
     * @param playerProfile the player profile to be updated.
     */
    @Override
    public void execute(PlayerProfile playerProfile) {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();
        playerProfile.loseHealth(HEALTH_LOSS);
        updatePlayer(playerProfile, game);
    }

    /**
     * Updates the player's profile based on the outcome of the typing game.
     *
     * @param player the player profile to be updated.
     * @param game the typing game to be evaluated.
     */
    private static void updatePlayer(PlayerProfile player, TypingGame game) {
        if (game.getAccuracy() >= PASS_ACCURACY) {
            assert game.getAccuracy() <= 100 : "Accuracy should not exceed 100";
            int reward = game.getAccuracy() * SALARY / PERCENT_RATIO;
            int earned = game.isOverTime() ? reward / 2 : reward;
            assert earned >= 0 : "Earned should not be negative";
            player.addAsset(earned);
        } else {
            indentPrint("You have failed the typing game and earned nothing.\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isAnAction() {
        return true;
    }
}
