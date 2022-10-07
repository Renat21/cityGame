import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    List<Player> players;

    private static final String[][] words = new String[][]{{"а", "А"}, {"б", "Б"},{"в", "В"},{"г", "Г"},{"д", "Д"},
            {"е", "Е"},{"ё", "Ё"},{"ж", "Ж"},{"з", "З"},{"и", "U"},{"к", "К"},{"л", "Л"},{"м", "М"},{"н", "Н"},
            {"о", "О"},{"п", "П"},{"р", "Р"},{"с", "С"},{"т", "Т"},{"у", "У"},{"ф", "Ф"},{"х", "Х"},{"ц", "Ц"},
            {"ч", "ч"},{"ш", "Ш"},{"щ", "Щ"},{"ь", "Ь"},{"ъ", "Ъ"},{"э", "Э"},{"ю", "Ю"},{"я", "Я"},{"й", "Й"}};

    private static final List<String> alreadyCity = new ArrayList<>();


    public static DatabaseConnection databaseConnection;

    static {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int playerNow = 0;

    String state = "game";

    public Game(List<Player> players) throws SQLException {
        this.players = players;
    }

    public void start() throws SQLException {
        System.out.println(" Uгра началась! Всего игроков " + players.size());
        Scanner scanner = new Scanner(System.in);
        String lastCharacter = "А";

        while (true) {
            System.out.println(" Ходит игрок под ником " + players.get(playerNow).getName() +
                    " на букву '" + lastCharacter + "'");
            System.out.print(players.get(playerNow).getName() + " : ");
            String city = scanner.nextLine();
            ResultSet resultSet = databaseConnection.getCityFromDatabase(city);

            // Пока игрок не введет нужный город
            while (!city.startsWith(lastCharacter) || !resultSet.next() || alreadyCity.contains(city)) {

                if (city.equals("over")) {
                    System.out.println(" Uгрок под ником " + players.get(playerNow).getName() + " выбыл.");
                    players.remove(playerNow);
                    state = "over";
                    if (players.size() == 1) {
                        System.out.println(" Uгрок под ником " + players.get(0).getName() + " победил!!!");
                        state = "winner";
                        break;
                    }
                    playerNow = playerNow % players.size();
                    break;
                } else if (!city.startsWith(lastCharacter))
                    System.out.println(" Введите город на нужную букву или сдайтесь (over)");
                else if (!resultSet.next())
                    System.out.println(" Введите существующий город или сдайтесь (over)");
                else if (alreadyCity.contains(city))
                    System.out.println(" Назовите не повторяющийся город или сдайтесь (over)");
                else break;


                System.out.print(players.get(playerNow).getName() + " : ");
                city = scanner.nextLine();
                resultSet = databaseConnection.getCityFromDatabase(city);
            }

            if (state.equals("winner"))
                break;

            if (!state.equals("over")) {
                String finalCity = city;
                lastCharacter = Arrays.stream(words).filter(i -> finalCity.endsWith(i[0])).toList().get(0)[1];
                alreadyCity.add(city);
                playerNow = (playerNow + 1) % players.size();
            }
            state = "game";
        }
    }
}
