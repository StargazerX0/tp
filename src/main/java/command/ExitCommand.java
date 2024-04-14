package command;

public class ExitCommand implements Command {


    public boolean isExit() {
        return true;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
