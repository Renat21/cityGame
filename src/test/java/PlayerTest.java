import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void start(){
        player = new Player("Renat", "Khakimov");
    }

    @Test
    public void setName(){
        player.setName("Taner");
        Assertions.assertEquals("Taner", player.getName());
    }

    @Test
    public void setSurname(){
        player.setSurname("Hakimov");
        Assertions.assertEquals("Hakimov", player.getSurname());
    }

    @Test
    public void setLocalDate(){
        player.setLocalDate("01.01.2022");
        Assertions.assertEquals("01.01.2022", player.getLocalDate());
    }

    @Test
    public void setId(){
        player.setId(1L);
        Assertions.assertEquals(1L, player.getId());
    }
}
