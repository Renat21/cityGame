
public class Player {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    private String name;
    private String Surname;
    private String localDate;

    public Player(String name, String surname) {
        this.name = name;
        Surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }
}
