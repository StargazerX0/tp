package command;

import minigame.TrueFalseGame;
import player.PlayerProfile;

public class RestCommand implements Command {

    /**
     * {@inheritDoc}
     *
     * Executes the rest command by starting the True and False game and update player's profile.
     *
     * @param playerProfile the player profile to be updated.
     */
    @Override
    public void execute(PlayerProfile playerProfile) {
        TrueFalseGame game = new TrueFalseGame();
        game.startGame();
        game.outputResult();
        if (game.getCorrectCount() >= 1) {
            playerProfile.addHealth(20);
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
