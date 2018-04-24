package ServiceEntites;

/**
 * @author Valeriy Knyazhev
 */
public class NewsCreator {

    private String email;
    private String name;

    public NewsCreator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String password) {
        this.email = email;
    }
}