public class GameManager {
    private boolean Continue;

    public GameManager(boolean ShouldContinue){this.Continue=ShouldContinue;}

    public boolean getContinueStatus() {
        return Continue;
    }

    public void setContinueStatus (boolean TurnComplete) {
        Continue= TurnComplete;
    }
}

