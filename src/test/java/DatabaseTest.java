import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {
    DatabaseConnection databaseConnection;

    @BeforeEach
    public void startWith() throws SQLException {
        databaseConnection = new DatabaseConnection();
    }

    @Test
    public void noSuchCityInDatabaseTest() throws SQLException {
        ResultSet resultSet = databaseConnection.getCityFromDatabase("Moscow");
        Assertions.assertThrows(org.postgresql.util.PSQLException.class,() -> resultSet.getString("city"));
    }

    @Test
    public void addCityToDataBase() throws SQLException {
        databaseConnection.insertCityToDB("newCityAndBigCity");
        ResultSet resultSet = databaseConnection.getCityFromDatabase("newCityAndBigCity");
        resultSet.next();
        Assertions.assertEquals("newCityAndBigCity", resultSet.getString("city"));
    }

    @Test
    public void cityIsCorrect() throws SQLException {
        databaseConnection.insertCityToDB("MyCity");
        ResultSet resultSet = databaseConnection.getCityFromDatabase("MyCity");
        resultSet.next();
        Assertions.assertEquals("MyCity", resultSet.getString("city"));
    }
}
