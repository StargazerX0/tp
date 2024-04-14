package command;

import minigame.TrueFalseGame;
import player.PlayerProfile;

public class RestCommand implements Command {
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
