package command;

import minigame.MCQGame;
import player.PlayerProfile;

public class RestCommand implements Command {
    @Override
    public void execute(PlayerProfile playerProfile) {
        MCQGame game = new MCQGame();
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
