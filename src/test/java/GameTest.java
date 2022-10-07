import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameTest {
    Game game;
    List<Player> players = new ArrayList<>();
    @BeforeEach
    public void startWith() throws SQLException {
        players.add(new Player("Renat", "Khakimov"));
        players.add(new Player("Nikita", "Jiznevsciy"));
        players.add(new Player("Max", "Maxevich"));
        game = new Game(players);
    }

    @Test
    public void addFourthPlayerTest(){
        Player newTestPlayer = new Player("Test", "Test");
        game.players.add(newTestPlayer);

        Assertions.assertTrue(game.players.contains(newTestPlayer));
    }

    @Test
    public void fastGameStartedAndFinishedTest() throws SQLException {
        String input = "over\nover";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.start();
    }

    @Test
    public void secondPlayerIsWinnerTest() throws SQLException {
        String input = "over\nAстрахань\nover";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        game.start();

        Assertions.assertEquals(0, game.playerNow);
    }

    @Test
    public void gameForTwoPlayerTest() throws SQLException {
        String input = "Aстана\nover";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.players.remove(0);

        game.start();

        Assertions.assertEquals("winner", game.state);
    }

}
