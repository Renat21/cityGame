import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException{
        List<Player> players = new ArrayList<>();
        players.add(new Player("Renat", "Khakimov"));
        players.add(new Player("Nikita", "Jiznevsciy"));
        players.add(new Player("Max", "Maxevich"));
        Game game = new Game(players);
        System.out.println(" Game started: \n");
        game.start();
    }
}
