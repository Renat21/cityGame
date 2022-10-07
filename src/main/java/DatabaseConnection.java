import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnection {
    private static final String DB_username = "postgres";
    private static final String DB_password = "123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cities";
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_username, DB_password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String SQL_SELECT= "SELECT city FROM jpa.cities where city like (?)";

    public DatabaseConnection() throws SQLException {
    }

    public ResultSet getCityFromDatabase(String city) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
        preparedStatement.setString(1,city);
        return preparedStatement.executeQuery();
    }

    public void writeAllCitiesToDBFromFile() throws FileNotFoundException, SQLException {
        PreparedStatement preparedStatement;

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/cities.txt"));

        String SQL_SELECT_TASKS = "INSERT INTO jpa.cities(city) VALUES (?)";
        preparedStatement = connection.prepareStatement(SQL_SELECT_TASKS);

        try {
            String line = br.readLine();
            if (!line.equals("")) {
                preparedStatement.setString(1,line);
                preparedStatement.executeUpdate();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCityToDB(String cityName) throws SQLException {
        PreparedStatement preparedStatement;
        String SQL_SELECT_TASKS = "INSERT INTO jpa.cities(city) VALUES (?)";
        preparedStatement = connection.prepareStatement(SQL_SELECT_TASKS);
        preparedStatement.setString(1,cityName);
        preparedStatement.executeUpdate();
    }
}
