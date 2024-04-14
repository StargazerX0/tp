package command;

import minigame.TypingGame;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class WorkCommand implements Command {
    private static final int SALARY = 2500;
    private static final int HEALTH_LOSS = 20;
    private static final int PASS_ACCURACY = 50;
    private static final int PERCENT_RATIO = 100;

    public void execute(PlayerProfile player) {
        TypingGame game = new TypingGame();
        game.startGame();
        game.outputResult();
        player.loseHealth(HEALTH_LOSS);
        updatePlayer(player, game);
    }

    private static void updatePlayer(PlayerProfile player, TypingGame game) {
        if (game.getAccuracy() >= PASS_ACCURACY) {
            assert game.getAccuracy() <= 100 : "Accuracy should not exceed 100";
            int reward = game.getAccuracy() * SALARY / PERCENT_RATIO;
            int earned = game.isOverTime() ? reward / 2 : reward;
            assert earned >= 0 : "Earned should not be negative";
            player.increaseAsset(earned);
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
