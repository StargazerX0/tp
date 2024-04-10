package command;

import minigame.TypingGame;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class WorkCommand implements Command {
    private static final int SALARY = 1000;

    public void execute(PlayerProfile player) {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();
        player.loseHealth(10);
        updatePlayer(player, game);
    }

    private static void updatePlayer(PlayerProfile player, TypingGame game) {
        if (game.getAccuracy() >= 50) {
            assert game.getAccuracy() <= 100 : "Accuracy should not exceed 100";
            int reward = game.getAccuracy() * SALARY / 100;
            int earned = game.isOverTime() ? reward / 2 : reward;
            assert earned >= 0 : "Earned should not be negative";
            player.addAsset(earned);
        } else {
            indentPrint("You have failed the typing game and earned nothing.\n");
        }
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return true;
    }
}
