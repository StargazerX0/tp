package command;

import minigame.TrueFalseGame;
import player.PlayerProfile;

public class RestCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        TrueFalseGame game = new TrueFalseGame();
        game.startGame();
        game.outputResult();
        if (game.getCorrectCount() >= 1) {
            playerProfile.addHealth(10);
        }
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return true;
    }
}
