import org.anmol.game.Game;
import org.anmol.game.GameFactory;
import org.junit.jupiter.api.Test;

public class GameTest {

    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTest() {
        Game game = gameFactory.createGame(3, 120);
    }

    @Test
    public void timeOutTestPerPlayer() {
        Game game = gameFactory.createGame(null, 120);
    }
}
